package br.com.cristiano.marvelheroes.utils

import br.com.cristiano.marvelheroes.http.apimodel.ErrorAPI

interface IListenerResult<R> {
    fun onResult(refId:Int,result: R)
    fun onError(refId:Int,error: ErrorAPI)
}

