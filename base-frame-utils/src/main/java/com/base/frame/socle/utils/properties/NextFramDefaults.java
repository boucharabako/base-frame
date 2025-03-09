/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.utils.properties;

/**
 *
 * @author W.KOUWONOU
 */
import java.util.*;

public class NextFramDefaults {
    private NextFramDefaults() {
    }
    static class Async {
        private Async() {
        }
        public static final int CORE_POOL_SIZE = 2;
        public static final int MAX_POOL_SIZE = 50;
        public static final int QUEUE_CAPACITY = 10000;
    }
    static class Http {
        private Http() {
        }
        public static final NextFramProperties.Http.Version VERSION = NextFramProperties.Http.Version.V_1_1;
        static class Cache {
            private Cache() {
            }
            public static final int TIME_TO_LIVE_IN_DAYS = 1461; // 4 years (including leap day)
        }
    }
    static class Cache {
        private Cache() {
        }
        static class Hazelcast {
            public static final int TIME_TO_LIVE_SECONDS = 3600; // 1 hour
            public static final int BACKUP_COUNT = 1;
            private Hazelcast() {
            }
            static class ManagementCenter {
                private ManagementCenter() {
                }
                public static final boolean ENABLE = false;
                public static final int UPDATE_INTERVAL = 3;
                public static final String URL = "";
            }
        }
        static class Ehcache {
            private Ehcache() {
            }
            public static final int TIME_TO_LIVE_SECONDS = 3600; // 1 hour
            public static final long MAX_ENTRIES = 100;
        }
        static class Infinispan {
            public static final String CONFIG_FILE = "default-configs/default-jgroups-tcp.xml";
            public static final boolean STATS_ENABLE = false;
            private Infinispan() {
            }
            static class Local {
                private Local() {
                }
                public static final long TIME_TO_LIVE_SECONDS = 60; // 1 minute
                public static final long MAX_ENTRIES = 100;
            }
            static class Distributed {

                private Distributed() {
                }
                public static final long TIME_TO_LIVE_SECONDS = 60; // 1 minute
                public static final long MAX_ENTRIES = 100;
                public static final int INSTANCE_COUNT = 1;
            }
            static class Replicated {
                private Replicated() {
                }
                public static final long TIME_TO_LIVE_SECONDS = 60; // 1 minute
                public static final long MAX_ENTRIES = 100;
            }
        }
    }

    static class Mail {
        public static final String HOST = "";
        public static final String MAIL_P = "";
        public static final int PORT = 25;
        public static final String PROTOCOL = "smtp";
        public static final String FROM = "";
        public static final String SENDER_NAME = "";
        public static final String BASE_URL = "";
        public static final String APP_LOGO_URL = "";

        private Mail() {
        } 
    }

    static class Security {
        private Security() {
        }
        static class ClientAuthorization {
            private ClientAuthorization() {
            }
            public static final String ACCESS_TOKEN_URI = null;
            public static final String TOKEN_SERVICE_ID = null;
            public static final String CLIENT_ID = null;
            public static final String CLIENT_SECRET = null;
        }
        static class Authentication {
            private Authentication() {
            }
            static class Jwt {
                private Jwt() {
                }
                public static final String SECRET = null;
                public static final long TOKEN_VALIDITY_IN_SECONDS = 1800;
                public static final long OKEN_VALIDITY_IN_SECONDS_FOR_REMEMBER_ME = 2592000;
            }
        }
        static class RememberMe {
            private RememberMe() {
            }
            public static final String KEY = null;
        }
    }
    static class Swagger {
        private Swagger() {
        }
        public static final String TITLE = "Application API";
        public static final String DESCRIPTION = "API documentation";
        public static final String VERSION = "0.0.1";
        public static final String TERMS_OF_SERVICE_URL = null;
        public static final String CONTACT_NAME = null;
        public static final String CONTACT_URL = null;
        public static final String CONTACT_EMAIL = null;
        public static final String LICENSE = null;
        public static final String LICENSE_URL = null;
        public static final String DEFULT_INCLUDE_PATTERN = "/api/.*";
        public static final String HOST = null;
        public static final String[] PROTOCOLS = {};
    }

    static class Metrics {
        private Metrics() {
        }
        static class Jmx {
            private Jmx() {
            }
            public static final boolean ENABLED = true;
        }
        static class Graphite {
            private Graphite() {
            }
            public static final boolean ENABLED = false;
            public static final String HOST = "";
            public static final int PORT = 2003;
            public static final String PREFIX = "jhipsterApplication";
        }

        static class Prometheus {
            private Prometheus() {
            }
            public static final boolean ENABLED = false;
            public static final String ENDPOINT = "/prometheusMetrics";
        }
        static class Logs {
            private Logs() {
            }
            public static final boolean ENABLED = false;
            public static final long REPORT_FREQUENCY = 60;
        }
    }
    static class Logging {
        private Logging() {
        }
        static class Logstash {
            private Logstash() {
            }
            public static final boolean ENABLED = false;
            public static final String HOST = "";
            public static final int PORT = 5000;
            public static final int QUEU_SIZE = 512;
        }
        static class SpectatorMetrics {
            private SpectatorMetrics() {
            }
            public static final boolean ENABLED = false;
        }
    }
    static class Social {

        private Social() {
        }

        public static final String REDIRECT_AFTER_SIGN_IN = "/#/home";
    }

    static class Gateway {
        private Gateway() {
        }
            protected static final Map<String, List<String>> AUTHORIZE_MICROSERVICES_ENDPOINTS = new LinkedHashMap<>();
        static class RateLimiting {

            private RateLimiting() {
            }
            public static final boolean ENABLED = false;
            public static final long LIMIT = 100_000L;
            public static final int DURATION_IN_SECONDS = 3_600;
        }
    }
    static class Ribbon {
        private Ribbon() {
        }
       protected static final String[] DISPALY_ON_ACTIVE_PROFILE = null;
    }
    static class Registry {
        private Registry() {
        }
        static String REGIST_P = null;
    }
    static class SMS {
        private SMS() {
        }
        public static final String URL = "";
        public static final String TEL_PARAM = "$num";
        public static final String MSG_PARAM = "$msg";
        public static final String SENDER_PARAM = "$sender";
    }
    static class GoogleRecaptchaKey {
        private GoogleRecaptchaKey() {
        }
        public static final String SITE = "";
        public static final String SECRET = "";
    }
    public static class DefaultClient {
        private DefaultClient() {
        }
        public static final String BASE_URL = "";
        public static final String ACTIVATION_URL = "";
        public static final String RESET_P_URL = "";
    }
    public static class AuthenticationParam {
        private AuthenticationParam() {
        }
        public static final String USERNAME = "username";
        public static final String AUTH_P = "";
        public static final String CODE = "code";
    }
    static class AppName {
        private AppName() {
        }
        public static final String APP_NAME = "prototype";
    }
}
