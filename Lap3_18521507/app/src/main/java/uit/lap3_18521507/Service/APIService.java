package uit.lap3_18521507.Service;

public class APIService {
    private static final String base_url = "https://newsapi.org";

    public static DataService getService() {
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
