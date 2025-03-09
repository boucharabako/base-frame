/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.utils.properties;

/**
 *
 * @author Alassani
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Properties specific to JHipster.
 *
 * <p>
 * Properties are configured in the application.yml file. </p>
 */
@Component
@ConfigurationProperties(prefix = "nframe", ignoreUnknownFields = false)
public class NextFramProperties {

    private final Async async = new Async();
    private final Http http = new Http();
    private final Cache cache = new Cache();
    private final Mail mail = new Mail();
    private final Security security = new Security();
    private final Swagger swagger = new Swagger();
    private final Metrics metrics = new Metrics();
    private final Logging logging = new Logging();
    private final CorsConfiguration cors = new CorsConfiguration();
    private final Social social = new Social();
    private final Gateway gateway = new Gateway();
    private final Ribbon ribbon = new Ribbon();
    private final Registry registry = new Registry();
    private final AuthenticationParam authParam = new AuthenticationParam();
    private final AppAuthenticationMode authenticationMode = new AppAuthenticationMode();
    private final Sms sms = new Sms();
    private final GoogleRecaptchaKey googleRecaptchaKey = new GoogleRecaptchaKey();
    private final DefaultClient defaultClient = new DefaultClient();
    private final DidocParam didocParam = new DidocParam();
    private final BienGateParam bienGateParam = new BienGateParam();

    private final BaseDeploimentABS baseDeploimentABS = new BaseDeploimentABS();
    private Integer paramReferentiel;

    public BaseDeploimentABS getBaseDeploimentABS() {
        return baseDeploimentABS;
    }

    public DidocParam getDidocParam() {
        return didocParam;
    }

    public BienGateParam getBienGateParam() {
        return bienGateParam;
    }

    public Async getAsync() {
        return async;
    }

    public Http getHttp() {
        return http;
    }

    public Cache getCache() {
        return cache;
    }

    public Mail getMail() {
        return mail;
    }

    public Registry getRegistry() {
        return registry;
    }

    public Security getSecurity() {
        return security;
    }

    public Swagger getSwagger() {
        return swagger;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public Logging getLogging() {
        return logging;
    }

    public CorsConfiguration getCors() {
        return cors;
    }

    public Social getSocial() {
        return social;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public Ribbon getRibbon() {
        return ribbon;
    }

    public AuthenticationParam getAuthParam() {
        return authParam;
    }

    public AppAuthenticationMode getAuthenticationMode() {
        return authenticationMode;
    }

    public Sms getSms() {
        return sms;
    }

    public String getAppName() {
        return NextFramDefaults.AppName.APP_NAME;
    }

    public GoogleRecaptchaKey getGoogleRecaptchaKey() {
        return googleRecaptchaKey;
    }

    public DefaultClient getDefaultClient() {
        return defaultClient;
    }

    public static class Async {

        private int corePoolSize = NextFramDefaults.Async.CORE_POOL_SIZE;
        private int maxPoolSize = NextFramDefaults.Async.MAX_POOL_SIZE;
        private int queueCapacity = NextFramDefaults.Async.QUEUE_CAPACITY;

        public int getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getMaxPoolSize() {
            return maxPoolSize;
        }

        public void setMaxPoolSize(int maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public int getQueueCapacity() {
            return queueCapacity;
        }

        public void setQueueCapacity(int queueCapacity) {
            this.queueCapacity = queueCapacity;
        }
    }

    public static class Http {

        public enum Version {
            V_1_1, V_2_0
        }
        private final Cache cache = new Cache();

        /**
         * HTTP version, must be "V_1_1" (for HTTP/1.1) or V_2_0 (for (HTTP/2)
         */
        private Version version = NextFramDefaults.Http.VERSION;

        public Cache getCache() {
            return cache;
        }

        public Version getVersion() {
            return version;
        }

        public void setVersion(Version version) {
            this.version = version;
        }

        public static class Cache {

            private int timeToLiveInDays = NextFramDefaults.Http.Cache.TIME_TO_LIVE_IN_DAYS;

            public int getTimeToLiveInDays() {
                return timeToLiveInDays;
            }

            public void setTimeToLiveInDays(int timeToLiveInDays) {
                this.timeToLiveInDays = timeToLiveInDays;
            }
        }
    }

    public static class Cache {

        private final Hazelcast hazelcast = new Hazelcast();
        private final Ehcache ehcache = new Ehcache();
        private final Infinispan infinispan = new Infinispan();

        public Hazelcast getHazelcast() {
            return hazelcast;
        }

        public Ehcache getEhcache() {
            return ehcache;
        }

        public Infinispan getInfinispan() {
            return infinispan;
        }

        public static class Hazelcast {

            private int timeToLiveSeconds = NextFramDefaults.Cache.Hazelcast.TIME_TO_LIVE_SECONDS;
            private int backupCount = NextFramDefaults.Cache.Hazelcast.BACKUP_COUNT;
            private final ManagementCenter managementCenter = new ManagementCenter();

            public ManagementCenter getManagementCenter() {
                return managementCenter;
            }

            public static class ManagementCenter {

                private boolean enabled = NextFramDefaults.Cache.Hazelcast.ManagementCenter.ENABLE;
                private int updateInterval = NextFramDefaults.Cache.Hazelcast.ManagementCenter.UPDATE_INTERVAL;
                private String url = NextFramDefaults.Cache.Hazelcast.ManagementCenter.URL;

                public boolean isEnabled() {
                    return enabled;
                }

                public void setEnabled(boolean enabled) {
                    this.enabled = enabled;
                }

                public int getUpdateInterval() {
                    return updateInterval;
                }

                public void setUpdateInterval(int updateInterval) {
                    this.updateInterval = updateInterval;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public int getTimeToLiveSeconds() {
                return timeToLiveSeconds;
            }

            public void setTimeToLiveSeconds(int timeToLiveSeconds) {
                this.timeToLiveSeconds = timeToLiveSeconds;
            }

            public int getBackupCount() {
                return backupCount;
            }

            public void setBackupCount(int backupCount) {
                this.backupCount = backupCount;
            }
        }

        public static class Ehcache {

            private int timeToLiveSeconds = NextFramDefaults.Cache.Ehcache.TIME_TO_LIVE_SECONDS;
            private long maxEntries = NextFramDefaults.Cache.Ehcache.MAX_ENTRIES;

            public int getTimeToLiveSeconds() {
                return timeToLiveSeconds;
            }

            public void setTimeToLiveSeconds(int timeToLiveSeconds) {
                this.timeToLiveSeconds = timeToLiveSeconds;
            }

            public long getMaxEntries() {
                return maxEntries;
            }

            public void setMaxEntries(long maxEntries) {
                this.maxEntries = maxEntries;
            }
        }

        public static class Infinispan {

            private String configFile = NextFramDefaults.Cache.Infinispan.CONFIG_FILE;
            private boolean statsEnabled = NextFramDefaults.Cache.Infinispan.STATS_ENABLE;
            private final Local local = new Local();
            private final Distributed distributed = new Distributed();
            private final Replicated replicated = new Replicated();

            public String getConfigFile() {
                return configFile;
            }

            public void setConfigFile(String configFile) {
                this.configFile = configFile;
            }

            public boolean isStatsEnabled() {
                return statsEnabled;
            }

            public void setStatsEnabled(boolean statsEnabled) {
                this.statsEnabled = statsEnabled;
            }

            public Local getLocal() {
                return local;
            }

            public Distributed getDistributed() {
                return distributed;
            }

            public Replicated getReplicated() {
                return replicated;
            }

            public static class Local {

                private long timeToLiveSeconds = NextFramDefaults.Cache.Infinispan.Local.TIME_TO_LIVE_SECONDS;
                private long maxEntries = NextFramDefaults.Cache.Infinispan.Local.MAX_ENTRIES;

                public long getTimeToLiveSeconds() {
                    return timeToLiveSeconds;
                }

                public void setTimeToLiveSeconds(long timeToLiveSeconds) {
                    this.timeToLiveSeconds = timeToLiveSeconds;
                }

                public long getMaxEntries() {
                    return maxEntries;
                }

                public void setMaxEntries(long maxEntries) {
                    this.maxEntries = maxEntries;
                }
            }

            public static class Distributed {

                private long timeToLiveSeconds = NextFramDefaults.Cache.Infinispan.Distributed.TIME_TO_LIVE_SECONDS;
                private long maxEntries = NextFramDefaults.Cache.Infinispan.Distributed.MAX_ENTRIES;
                private int instanceCount = NextFramDefaults.Cache.Infinispan.Distributed.INSTANCE_COUNT;

                public long getTimeToLiveSeconds() {
                    return timeToLiveSeconds;
                }

                public void setTimeToLiveSeconds(long timeToLiveSeconds) {
                    this.timeToLiveSeconds = timeToLiveSeconds;
                }

                public long getMaxEntries() {
                    return maxEntries;
                }

                public void setMaxEntries(long maxEntries) {
                    this.maxEntries = maxEntries;
                }

                public int getInstanceCount() {
                    return instanceCount;
                }

                public void setInstanceCount(int instanceCount) {
                    this.instanceCount = instanceCount;
                }
            }

            public static class Replicated {

                private long timeToLiveSeconds = NextFramDefaults.Cache.Infinispan.Replicated.TIME_TO_LIVE_SECONDS;
                private long maxEntries = NextFramDefaults.Cache.Infinispan.Replicated.MAX_ENTRIES;

                public long getTimeToLiveSeconds() {
                    return timeToLiveSeconds;
                }

                public void setTimeToLiveSeconds(long timeToLiveSeconds) {
                    this.timeToLiveSeconds = timeToLiveSeconds;
                }

                public long getMaxEntries() {
                    return maxEntries;
                }

                public void setMaxEntries(long maxEntries) {
                    this.maxEntries = maxEntries;
                }
            }
        }
    }

    public static class Mail {

        private String from = NextFramDefaults.Mail.FROM;
        private String senderName = NextFramDefaults.Mail.SENDER_NAME;
        private String baseUrl = NextFramDefaults.Mail.BASE_URL;
        private String host = NextFramDefaults.Mail.HOST;
        private String password = NextFramDefaults.Mail.MAIL_P;
        private int port = NextFramDefaults.Mail.PORT;
        private String protocol = NextFramDefaults.Mail.PROTOCOL;
        private String appLogoUrl = NextFramDefaults.Mail.APP_LOGO_URL;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getSenderName() {
            return senderName;
        }

        public void setSenderName(String senderName) {
            this.senderName = senderName;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public String getAppLogoUrl() {
            return appLogoUrl;
        }

        public void setAppLogoUrl(String appLogoUrl) {
            this.appLogoUrl = appLogoUrl;
        }

    }

    public static class Security {

        private final ClientAuthorization clientAuthorization = new ClientAuthorization();
        private final Authentication authentication = new Authentication();
        private final RememberMe rememberMe = new RememberMe();

        public ClientAuthorization getClientAuthorization() {
            return clientAuthorization;
        }

        public Authentication getAuthentication() {
            return authentication;
        }

        public RememberMe getRememberMe() {
            return rememberMe;
        }

        public static class ClientAuthorization {

            private String accessTokenUri = NextFramDefaults.Security.ClientAuthorization.ACCESS_TOKEN_URI;
            private String tokenServiceId = NextFramDefaults.Security.ClientAuthorization.TOKEN_SERVICE_ID;
            private String clientId = NextFramDefaults.Security.ClientAuthorization.CLIENT_ID;
            private String clientSecret = NextFramDefaults.Security.ClientAuthorization.CLIENT_SECRET;

            public String getAccessTokenUri() {
                return accessTokenUri;
            }

            public void setAccessTokenUri(String accessTokenUri) {
                this.accessTokenUri = accessTokenUri;
            }

            public String getTokenServiceId() {
                return tokenServiceId;
            }

            public void setTokenServiceId(String tokenServiceId) {
                this.tokenServiceId = tokenServiceId;
            }

            public String getClientId() {
                return clientId;
            }

            public void setClientId(String clientId) {
                this.clientId = clientId;
            }

            public String getClientSecret() {
                return clientSecret;
            }

            public void setClientSecret(String clientSecret) {
                this.clientSecret = clientSecret;
            }
        }

        public static class Authentication {

            private final Jwt jwt = new Jwt();

            public Jwt getJwt() {
                return jwt;
            }

            public static class Jwt {

                private String secret = NextFramDefaults.Security.Authentication.Jwt.SECRET;
                private long tokenValidityInSeconds = NextFramDefaults.Security.Authentication.Jwt.TOKEN_VALIDITY_IN_SECONDS;
                private long tokenValidityInSecondsForRememberMe = NextFramDefaults.Security.Authentication.Jwt.TOKEN_VALIDITY_IN_SECONDS;

                public String getSecret() {
                    return secret;
                }

                public void setSecret(String secret) {
                    this.secret = secret;
                }

                public long getTokenValidityInSeconds() {
                    return tokenValidityInSeconds;
                }

                public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
                    this.tokenValidityInSeconds = tokenValidityInSeconds;
                }

                public long getTokenValidityInSecondsForRememberMe() {
                    return tokenValidityInSecondsForRememberMe;
                }

                public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
                    this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
                }
            }
        }

        public static class RememberMe {

            private String key = NextFramDefaults.Security.RememberMe.KEY;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }
        }
    }

    public static class Swagger {

        private String title = NextFramDefaults.Swagger.TITLE;
        private String description = NextFramDefaults.Swagger.DESCRIPTION;
        private String version = NextFramDefaults.Swagger.VERSION;
        private String termsOfServiceUrl = NextFramDefaults.Swagger.TERMS_OF_SERVICE_URL;
        private String contactName = NextFramDefaults.Swagger.CONTACT_NAME;
        private String contactUrl = NextFramDefaults.Swagger.CONTACT_URL;
        private String contactEmail = NextFramDefaults.Swagger.CONTACT_EMAIL;
        private String license = NextFramDefaults.Swagger.LICENSE;
        private String licenseUrl = NextFramDefaults.Swagger.LICENSE_URL;
        private String defaultIncludePattern = NextFramDefaults.Swagger.DEFULT_INCLUDE_PATTERN;
        private String host = NextFramDefaults.Swagger.HOST;
        private String[] protocols = NextFramDefaults.Swagger.PROTOCOLS;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getTermsOfServiceUrl() {
            return termsOfServiceUrl;
        }

        public void setTermsOfServiceUrl(String termsOfServiceUrl) {
            this.termsOfServiceUrl = termsOfServiceUrl;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getContactUrl() {
            return contactUrl;
        }

        public void setContactUrl(String contactUrl) {
            this.contactUrl = contactUrl;
        }

        public String getContactEmail() {
            return contactEmail;
        }

        public void setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
        }

        public String getLicense() {
            return license;
        }

        public void setLicense(String license) {
            this.license = license;
        }

        public String getLicenseUrl() {
            return licenseUrl;
        }

        public void setLicenseUrl(String licenseUrl) {
            this.licenseUrl = licenseUrl;
        }

        public String getDefaultIncludePattern() {
            return defaultIncludePattern;
        }

        public void setDefaultIncludePattern(String defaultIncludePattern) {
            this.defaultIncludePattern = defaultIncludePattern;
        }

        public String getHost() {
            return host;
        }

        public void setHost(final String host) {
            this.host = host;
        }

        public String[] getProtocols() {
            return protocols;
        }

        public void setProtocols(final String[] protocols) {
            this.protocols = protocols;
        }
    }

    public static class Metrics {

        private final Jmx jmx = new Jmx();
        private final Graphite graphite = new Graphite();
        private final Prometheus prometheus = new Prometheus();
        private final Logs logs = new Logs();

        public Jmx getJmx() {
            return jmx;
        }

        public Graphite getGraphite() {
            return graphite;
        }

        public Prometheus getPrometheus() {
            return prometheus;
        }

        public Logs getLogs() {
            return logs;
        }

        public static class Jmx {

            private boolean enabled = NextFramDefaults.Metrics.Jmx.ENABLED;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }
        }

        public static class Graphite {

            private boolean enabled = NextFramDefaults.Metrics.Graphite.ENABLED;
            private String host = NextFramDefaults.Metrics.Graphite.HOST;
            private int port = NextFramDefaults.Metrics.Graphite.PORT;
            private String prefix = NextFramDefaults.Metrics.Graphite.PREFIX;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getPort() {
                return port;
            }

            public void setPort(int port) {
                this.port = port;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }
        }

        public static class Prometheus {

            private boolean enabled = NextFramDefaults.Metrics.Prometheus.ENABLED;
            private String endpoint = NextFramDefaults.Metrics.Prometheus.ENDPOINT;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getEndpoint() {
                return endpoint;
            }

            public void setEndpoint(String endpoint) {
                this.endpoint = endpoint;
            }
        }

        public static class Logs {

            private boolean enabled = NextFramDefaults.Metrics.Logs.ENABLED;
            private long reportFrequency = NextFramDefaults.Metrics.Logs.REPORT_FREQUENCY;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public long getReportFrequency() {
                return reportFrequency;
            }

            public void setReportFrequency(long reportFrequency) {
                this.reportFrequency = reportFrequency;
            }
        }
    }

    public static class Logging {

        private final Logstash logstash = new Logstash();

        public Logstash getLogstash() {
            return logstash;
        }

        public static class Logstash {

            private boolean enabled = NextFramDefaults.Logging.Logstash.ENABLED;
            private String host = NextFramDefaults.Logging.Logstash.HOST;
            private int port = NextFramDefaults.Logging.Logstash.PORT;
            private int queueSize = NextFramDefaults.Logging.Logstash.QUEU_SIZE;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getHost() {
                return host;
            }

            public void setHost(String host) {
                this.host = host;
            }

            public int getPort() {
                return port;
            }

            public void setPort(int port) {
                this.port = port;
            }

            public int getQueueSize() {
                return queueSize;
            }

            public void setQueueSize(int queueSize) {
                this.queueSize = queueSize;
            }
        }
        private final SpectatorMetrics spectatorMetrics = new SpectatorMetrics();

        public SpectatorMetrics getSpectatorMetrics() {
            return spectatorMetrics;
        }

        public static class SpectatorMetrics {

            private boolean enabled = NextFramDefaults.Logging.SpectatorMetrics.ENABLED;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }
        }
    }

    public static class Social {

        private String redirectAfterSignIn = NextFramDefaults.Social.REDIRECT_AFTER_SIGN_IN;

        public String getRedirectAfterSignIn() {
            return redirectAfterSignIn;
        }

        public void setRedirectAfterSignIn(String redirectAfterSignIn) {
            this.redirectAfterSignIn = redirectAfterSignIn;
        }
    }

    public static class Gateway {

        private final RateLimiting rateLimiting = new RateLimiting();

        public RateLimiting getRateLimiting() {
            return rateLimiting;
        }
        private Map<String, List<String>> authorizedMicroservicesEndpoints = NextFramDefaults.Gateway.AUTHORIZE_MICROSERVICES_ENDPOINTS;

        public Map<String, List<String>> getAuthorizedMicroservicesEndpoints() {
            return authorizedMicroservicesEndpoints;
        }

        public void setAuthorizedMicroservicesEndpoints(Map<String, List<String>> authorizedMicroservicesEndpoints) {
            this.authorizedMicroservicesEndpoints = authorizedMicroservicesEndpoints;
        }

        public static class RateLimiting {

            private boolean enabled = NextFramDefaults.Gateway.RateLimiting.ENABLED;
            private long limit = NextFramDefaults.Gateway.RateLimiting.LIMIT;
            private int durationInSeconds = NextFramDefaults.Gateway.RateLimiting.DURATION_IN_SECONDS;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public long getLimit() {
                return this.limit;
            }

            public void setLimit(long limit) {
                this.limit = limit;
            }

            public int getDurationInSeconds() {
                return durationInSeconds;
            }

            public void setDurationInSeconds(int durationInSeconds) {
                this.durationInSeconds = durationInSeconds;
            }
        }
    }

    public static class Ribbon {

        private String[] displayOnActiveProfiles = NextFramDefaults.Ribbon.DISPALY_ON_ACTIVE_PROFILE;

        public String[] getDisplayOnActiveProfiles() {
            return displayOnActiveProfiles;
        }

        public void setDisplayOnActiveProfiles(String[] displayOnActiveProfiles) {
            this.displayOnActiveProfiles = displayOnActiveProfiles;
        }
    }

    public static class Registry {

        private String password = NextFramDefaults.Registry.REGIST_P;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class AuthenticationParam {

        private String useranme = NextFramDefaults.AuthenticationParam.USERNAME;
        private String password = NextFramDefaults.AuthenticationParam.AUTH_P;
        private String code = NextFramDefaults.AuthenticationParam.CODE;

        public String getUseranme() {
            return useranme;
        }

        public void setUseranme(String useranme) {
            this.useranme = useranme;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public static class AppAuthenticationMode {

        private boolean basicAuthenticationAccess = true;
        private boolean doubleAuthenticationAccess = false;
        private boolean captchaAuthenticationAccess = false;
        private boolean transactionSecure = false;
        private boolean mobileDeviceAuthenticationEnabled = false;
        private long codeExpiration = 300;
        private int codeSize = 4;

        public boolean isMobileDeviceAuthenticationEnabled() {
            return mobileDeviceAuthenticationEnabled;
        }

        public void setMobileDeviceAuthenticationEnabled(boolean mobileDeviceAuthenticationEnabled) {
            this.mobileDeviceAuthenticationEnabled = mobileDeviceAuthenticationEnabled;
        }

        public boolean isBasicAuthenticationAccess() {
            return basicAuthenticationAccess;
        }

        public boolean isTransactionSecure() {
            return transactionSecure;
        }

        public void setTransactionSecure(boolean transactionSecure) {
            this.transactionSecure = transactionSecure;
        }

        public int getCodeSize() {
            return codeSize;
        }

        public void setCodeSize(int codeSize) {
            this.codeSize = codeSize;
        }

        public long getCodeExpiration() {
            return codeExpiration;
        }

        public void setCodeExpiration(long codeExpiration) {
            this.codeExpiration = codeExpiration;
        }

        public void setBasicAuthenticationAccess(boolean basicAuthenticationAccess) {
            this.basicAuthenticationAccess = basicAuthenticationAccess;
        }

        public boolean isDoubleAuthenticationAccess() {
            return doubleAuthenticationAccess;
        }

        public void setDoubleAuthenticationAccess(boolean doubleAuthenticationAccess) {
            this.doubleAuthenticationAccess = doubleAuthenticationAccess;
        }

        public boolean isCaptchaAuthenticationAccess() {
            return captchaAuthenticationAccess;
        }

        public void setCaptchaAuthenticationAccess(boolean captchaAuthenticationAccess) {
            this.captchaAuthenticationAccess = captchaAuthenticationAccess;
        }
    }

    public static class Sms {

        private String url = NextFramDefaults.SMS.URL;
        private String telParam = NextFramDefaults.SMS.TEL_PARAM;
        private String msgParam = NextFramDefaults.SMS.MSG_PARAM;
        private String senderParam = NextFramDefaults.SMS.SENDER_PARAM;

        public String getSenderParam() {
            return senderParam;
        }

        public void setSenderParam(String senderParam) {
            this.senderParam = senderParam;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTelParam() {
            return telParam;
        }

        public void setTelParam(String telParam) {
            this.telParam = telParam;
        }

        public String getMsgParam() {
            return msgParam;
        }

        public void setMsgParam(String msgParam) {
            this.msgParam = msgParam;
        }
    }

    public static class GoogleRecaptchaKey {

        private String site = NextFramDefaults.GoogleRecaptchaKey.SITE;
        private String secret = NextFramDefaults.GoogleRecaptchaKey.SECRET;

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }

    public static class DefaultClient {

        private String baseUrl = NextFramDefaults.DefaultClient.BASE_URL;
        private String activationUrl = NextFramDefaults.DefaultClient.RESET_P_URL;
        private String resetPasswordUrl = NextFramDefaults.DefaultClient.BASE_URL;

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public String getActivationUrl() {
            return activationUrl;
        }

        public void setActivationUrl(String activationUrl) {
            this.activationUrl = activationUrl;
        }

        public String getResetPasswordUrl() {
            return resetPasswordUrl;
        }

        public void setResetPasswordUrl(String resetPasswordUrl) {
            this.resetPasswordUrl = resetPasswordUrl;
        }
    }

    public static class DidocParam {

        private String url;
        private String username;
        private String pwd;
        private String espaceDoc;
        private List<String> espaceDocumentaire;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public List<String> getEspaceDocumentaire() {
            return getEspaceDoc() != null ? Arrays.asList(getEspaceDoc().split(",")) : new ArrayList<>();
        }

        public void setEspaceDocumentaire(List<String> espaceDocumentaire) {
            this.espaceDocumentaire = espaceDocumentaire;
        }

        public String getEspaceDoc() {
            return espaceDoc;
        }

        public void setEspaceDoc(String espaceDoc) {
            this.espaceDoc = espaceDoc;
        }

    }

    public static class BienGateParam {

        private String url;
        private String username;
        private String pwd;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
    }

    public static class BaseDeploimentABS {

        private String baseABS;

        public String getBaseABS() {
            return baseABS;
        }

        public void setBaseABS(String baseABS) {
            this.baseABS = baseABS;
        }

    }

    public Integer getParamReferentiel() {
        return paramReferentiel;
    }

    public void setParamReferentiel(Integer paramReferentiel) {
        this.paramReferentiel = paramReferentiel;
    }
}
