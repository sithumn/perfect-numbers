package org.perfectnumbers.service;

public class RestServiceFactory {

    public static IRestService getRestService(final ServiceType service) {
        switch (service) {
            case CHECK_PERFECT_NUMBER_SERVICE:
                return new CheckPerfectNumberService();
            case GET_PERFECT_NUMBERS_SERVICE:
                return new GetPerfectNumberService();
            default:
                return null;
        }
    }

    public enum ServiceType {
        CHECK_PERFECT_NUMBER_SERVICE,
        GET_PERFECT_NUMBERS_SERVICE
    }

}
