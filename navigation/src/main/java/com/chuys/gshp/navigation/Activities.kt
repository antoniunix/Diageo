package com.chuys.gshp.navigation

enum class Activities(val activity: String) {
    SPLASH(".splash.view.Splash"),
    LOGIN(".login.view.Login"),
    TERMS(".login.view.Terms"),
    HOME(".home.view.Home"),
    PDV_LIST(".pdv.view.ListPdv"),
    PDV_ADD(""),
    PDV_HISTORIC(""),
    MENU_REPORT(".reportmenu.view.ReportMenu"),
    POLL(""),
    ORDER(".order.view.Orders"),
    PRICE_AND_AVAILABILITY(".sku.view.Sku"),
    EXECUTABLE(".executables.view.Executable"),
    VISIT(".visits.view.Visit"),
    COMMUNICATION(".comunication.view.Comunication"),
    HELP(""),
    GEOLOCATION(".geolocation.view.GeolocationActivity"),
    DEFAULT("")
}