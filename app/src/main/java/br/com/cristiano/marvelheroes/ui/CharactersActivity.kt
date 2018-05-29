package br.com.cristiano.marvelheroes.ui

import android.app.SearchManager
import android.content.ComponentName
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import br.com.cristiano.marvelheroes.adapters.CharactersAdapter
import br.com.cristiano.marvelheroes.http.apimodel.ErrorAPI
import br.com.cristiano.marvelheroes.http.apimodel.ResponseAPI
import br.com.cristiano.marvelheroes.utils.IListenerResult
import br.com.cristiano.marvelheroes.utils.toaster

import android.content.Context
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.view.View
import br.com.cristiano.marvelheroes.R
import br.com.cristiano.marvelheroes.control.AppControl
import br.com.cristiano.marvelheroes.http.apimodel.Character
import br.com.cristiano.marvelheroes.utils.PaginationScrollListener
import br.com.cristiano.marvelheroes.utils.WaitDialog
import br.com.cristiano.marvelheroes.utils.WaitDialog.closeWaitDialog
import br.com.cristiano.marvelheroes.utils.WaitDialog.showWaitDialog
import kotlinx.android.synthetic.main.activity_characters.*


class CharactersActivity : AppCompatActivity() {

    private lateinit var app: AppControl
    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val P_TAG = "CharactersActivity"
    private var list = ArrayList<Character>()
    private var initialPage:Int = 0
    private var word:String = ""
    private var isLoading = false
    private var isLastPage = false
    private var totalItens = 0
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_marvel)

        app = AppControl(this)
        void_state.visibility = View.VISIBLE


        linearLayoutManager = LinearLayoutManager(this@CharactersActivity)
        recycler_view.setLayoutManager(linearLayoutManager)
        charactersAdapter = CharactersAdapter(this@CharactersActivity,list )
        recycler_view.adapter = charactersAdapter
        recycler_view.addOnScrollListener(object:PaginationScrollListener(linearLayoutManager){
            override fun loadMoreItems() {
                this@CharactersActivity.isLoading = true
                currentPage += 10

                if (currentPage < totalItens){
                    getList(word,currentPage)
                }
            }

            override fun getTotalPageCount(): Int {
                return this@CharactersActivity.list.size
            }

            override fun isLastPage(): Boolean {
                return this@CharactersActivity.isLastPage
            }

            override fun isLoading(): Boolean {
                return this@CharactersActivity.isLoading
            }

        })


    }

    fun getList(word:String,starPage:Int){
        this@CharactersActivity.word = word
        this@CharactersActivity.isLoading = true
        showWaitDialog(this)
        app.getCharacters(word,starPage,object:IListenerResult<ResponseAPI> {
            override fun onResult(refId: Int, result: ResponseAPI) {
                closeWaitDialog(object:WaitDialog.IListenerExecute<Boolean>{
                    override fun onExecuted(result: Boolean) {
                        this@CharactersActivity.isLoading = !result
                    }

                })
                this@CharactersActivity.list.addAll(result.data.results)
                this@CharactersActivity.totalItens = result.data.total
                charactersAdapter.notifyDataSetChanged()
                if (list.isNotEmpty()){
                    void_state.visibility = View.GONE
                }else{
                    void_state.visibility = View.VISIBLE
                }


            }
            override fun onError(refId: Int, error: ErrorAPI) {
                closeWaitDialog()
                Log.v(P_TAG,error.toString())
                toaster(error.getMessage())
                void_state.visibility = View.VISIBLE
            }

        })

    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchItem = menu.findItem(R.id.mn_bar_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                newSearch(query?:"")
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        searchView.setOnCloseListener(object :SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                newSearch("")
                return false
            }
        })

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val componentName = ComponentName(this@CharactersActivity, CharactersActivity::class.java)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        return super.onCreateOptionsMenu(menu)
    }

    private fun newSearch(query: String) {
        list.clear()
        this@CharactersActivity.isLoading = false
        this@CharactersActivity.isLastPage = false
        this@CharactersActivity.totalItens = 0
        this@CharactersActivity.currentPage = 0

        getList(query,0)
    }


}
