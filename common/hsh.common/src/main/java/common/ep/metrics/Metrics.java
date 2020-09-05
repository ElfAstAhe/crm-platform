package common.ep.metrics;

/**
 * Константы с метриками
 *
 * @author elf
 */
public class Metrics {
    public static class Metrica {
        public static final String TOTAL_HTTP_REQUEST = "app_total_http_request";
        public static final String ACTIVE_HTTP_REQUEST = "app_active_http_request";
        public static final String REQUEST_SUMMARY_SIZE_BYTES = "app_request_summary_size_bytes";
        public static final String REQUEST_SUMMARY_LATENCY_SECONDS = "app_request_summary_latency_seconds";
        public static final String REQUEST_HISTOGRAM_LATENCY_SECONDS = "app_request_histogram_latency_seconds";
    }

    public static class Help {
        public static final String TOTAL_HTTP_REQUEST = "Total number of HTTP requests";
        public static final String ACTIVE_HTTP_REQUEST = "Active number of HTTP requests";
        public static final String REQUEST_SUMMARY_SIZE_BYTES = "Request summary size in bytes";
        public static final String REQUEST_SUMMARY_LATENCY_SECONDS = "Request summary latency";
        public static final String REQUEST_HISTOGRAM_LATENCY_SECONDS = "Request histogram latency";
    }

    public static class Label {
        public static final String STATUS = "status";
        public static final String PATH_URI = "path_uri";
        public static final String METHOD = "method";
    }

    private Metrics() {
        // hide constructor
    }
}
