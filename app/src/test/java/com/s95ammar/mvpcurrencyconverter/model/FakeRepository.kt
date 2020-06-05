package com.s95ammar.mvpcurrencyconverter.model

import io.reactivex.Single

class FakeRepository : IRepository {

    override fun getCountryCurrency(countryCode: String): Single<CountryCurrencyResponse.Currency> {
        return Single.just(CountryCurrencyResponse.Currency("UAH", "Ukrainian hryvnia", "₴"))
    }

    override fun getRates(from: String): Single<ConversionResponse> {
        val response = ConversionResponse(
            amount = 1.0,
            baseCurrencyCode = "UAH",
            baseCurrencyName = "Ukrainian Hryvnia",
            rates = mapOf(
                ("AED" to ConversionResponse.TargetCurrency("United Arab Emirates Dirham", 0.1382, 0.1382)),
                ("ALL" to ConversionResponse.TargetCurrency("Albanian Lek", 4.1212, 4.1212)),
                ("AMD" to ConversionResponse.TargetCurrency("Armenian Dram", 17.9919, 17.9919)),
                ("ANG" to ConversionResponse.TargetCurrency("Netherlands Antillean Guilder", 0.0676, 0.0676)),
                ("ARS" to ConversionResponse.TargetCurrency("Argentine Peso", 2.5933, 2.5933)),
                ("AUD" to ConversionResponse.TargetCurrency("Australian Dollar", 0.0539, 0.0539)),
                ("AZN" to ConversionResponse.TargetCurrency("Azerbaijani Manat", 0.0641, 0.0641)),
                ("BDT" to ConversionResponse.TargetCurrency("Bangladeshi Taka", 3.1969, 3.1969)),
                ("BGN" to ConversionResponse.TargetCurrency("Bulgarian Lev", 0.0649, 0.0649)),
                ("BHD" to ConversionResponse.TargetCurrency("Bahraini Dinar", 0.0142, 0.0142)),
                ("BND" to ConversionResponse.TargetCurrency("Brunei Dollar", 0.0525, 0.0525))
            ),
            updatedDate = "2020-06-05"
        )
        return Single.just(response)
    }

    override fun getRateHistory(date: String, from: String, to: String): Single<ConversionResponse> {
        val response = ConversionResponse(
            amount = 1.0,
            baseCurrencyCode = "USD",
            baseCurrencyName = "US Dollar",
            rates = mapOf("UAH" to ConversionResponse.TargetCurrency("Ukrainian Hryvnia", 26.9842, 26.9842)),
            updatedDate = "2020-05-27"
        )
        return Single.just(response)
    }

}