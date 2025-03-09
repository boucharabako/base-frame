//
//package com.base.frame.socle.core.workflow.service;
//
//import com.ngs.core.codification.dto.LicenceDto;
//import com.ngs.core.codification.entities.licence.Licence;
//import com.ngs.core.codification.exception.LicenceRuntimeException;
//import com.ngs.core.codification.repositories.LicenceRepository;
//import com.ngs.core.utils.RandomUtil;
//import java.time.Instant;
//import java.util.List;
//import java.util.logging.Logger;
//import javax.annotation.PostConstruct;
//import org.json.JSONException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//
///**
// *
// * @author kouwonou
// */
//@Service
//@Transactional
//public class LicenceService {
//
////    String licenceUrl = "http://192.168.1.19:8088/licence/api/vi/";
////       String licenceUrl = "https://ns3011948.ip-46-105-113.eu/licence-validator/api/vi/";
//    String licenceUrl = "http://ns3011948.ip-46-105-113.eu:8080/licence/api/vi/";
//    String licenceServer = licenceUrl + "licence";
//    String licenceServerInstall = licenceUrl + "licenceInstall";
//    String licenceServerVerif = licenceUrl + "licenceVerif";
//    private static final Logger LOG = Logger.getLogger(LicenceService.class.getName());
//
//    private static final String SERVER_OUT_OF_SERCICE = "Le serveur de licence ne repond pas";
//    private static final String LICENCE_TABLE_ALTERED = "La table des licences  a ete altérée";
//
//    RestTemplate restTemplate = new RestTemplate();
//    @Autowired
//    private LicenceRepository licenceRepository;
//
//    @Autowired(required = false)
//    private GetLicenceService getLicenceService;
//
//    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    public final static String ETAT_INACTIF = "0";
//    public final static String ETAT_ACTIF = "1";
//    public final static String ETAT_EXPIRE = "2";
//    public final static String TYPE_LICENCE_TEMPORAIRE = "T";
//    public final static String TYPE_LICENCE_DEFINITIF = "D";
//
//    public final static String TYPE_INSTALLATION_TEST = "test";
//    public final static String TYPE_INSTALLATION_PROD = "prod";
//    HttpHeaders headers;
//
//    @PostConstruct
//    @Scheduled(fixedRate = 86400000)
////        @Scheduled(fixedRate = 300000)
//    public void init() throws JSONException {
//        LOG.info("-----------------------------> Validation de la licence");
//        headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> request = new HttpEntity<>(headers);
//        if (getLicenceService != null) {
//            List<Licence> l = licenceRepository.findAll();
//
//            if (l.isEmpty()) {
//                if (getLicenceService.getLicence() == null || getLicenceService.getLicence().isEmpty()) {
//                    throw new LicenceRuntimeException("Veuillez activer ce produit");
//                }
//                if (getLicenceService.getTypeInsatallation() == null
//                        || getLicenceService.getTypeInsatallation().isEmpty() || ("").equalsIgnoreCase(getLicenceService.getTypeInsatallation())) {
//                    throw new LicenceRuntimeException("Veuillez définir le type d'installation du produit");
//                }
//                System.out.println("getLicenceService.getTypeInsatallation() g " + getLicenceService.getTypeInsatallation());
//
//                if (!(TYPE_INSTALLATION_TEST).equalsIgnoreCase(getLicenceService.getTypeInsatallation())
//                        && !(TYPE_INSTALLATION_PROD).equalsIgnoreCase(getLicenceService.getTypeInsatallation())) {
//                    throw new LicenceRuntimeException("Le type d'installation défini du produit est incorrect");
//                }
//
//                ResponseEntity<LicenceDto> res = null;
//
//                try {
//                    res = restTemplate.exchange(licenceServer + "?cle=" + getLicenceService.getLicence(), HttpMethod.GET, request, LicenceDto.class);
//                    if (res.getStatusCode() != HttpStatus.OK) {
//                        throw new LicenceRuntimeException("Le serveur n'a pas pu valider la clé d'activation");
//
//                    }
//                } catch (RestClientException e) {
//                    e.printStackTrace();
//                    throw new LicenceRuntimeException(SERVER_OUT_OF_SERCICE + " 85");
//
//                }
//
//                if (!res.hasBody()) {
//                    throw new LicenceRuntimeException(SERVER_OUT_OF_SERCICE + "86");
//
//                }
//                if (res.getBody().getCodeStatut() == null || res.getBody().getCodeStatut().equals(ETAT_EXPIRE)) {
//                    throw new LicenceRuntimeException("La clé de licence est deja utilisée ");
//                }
//                if ((TYPE_INSTALLATION_TEST).equalsIgnoreCase(getLicenceService.getTypeInsatallation())
//                        && (res.getBody().getNbrInstallTest() != null)
//                        && (res.getBody().getNbrInstallTest() >= res.getBody().getNbrInstallTestParam())) {
//                    throw new LicenceRuntimeException("La limite d'installation de test de la clé est atteinte");
//                }
//                if ((TYPE_INSTALLATION_PROD).equalsIgnoreCase(getLicenceService.getTypeInsatallation())
//                        && (res.getBody().getNbrInstallProd() != null)
//                        && (res.getBody().getNbrInstallProd() >= res.getBody().getNbrInstallProdParam())) {
//                    throw new LicenceRuntimeException("La limite d'installation de production de la clé est atteinte");
//                }
//                LicenceDto li = res.getBody();
//                li.setCodeInstallation(RandomUtil.generatecode(40));
//                li.setTypeInstallation(getLicenceService.getTypeInsatallation());
//                HttpEntity<LicenceDto> request2 = new HttpEntity<>(li, headers);
//                ResponseEntity<LicenceDto> resp = null;
//                try {
//                    resp = restTemplate.exchange(licenceServerInstall, HttpMethod.POST, request2, LicenceDto.class);
//
//                } catch (RestClientException e) {
//                    throw new LicenceRuntimeException(SERVER_OUT_OF_SERCICE + " 852");
//
//                }
//                if (!resp.hasBody()) {
//                    throw new LicenceRuntimeException(SERVER_OUT_OF_SERCICE + "8696");
//
//                }
//                if (resp.getBody().getCodeStatut() == null || resp.getBody().getCodeStatut().equals(ETAT_EXPIRE)) {
//                    throw new LicenceRuntimeException("La clé de licence est deja utilisée ");
//                }
//
//                Licence licence = new Licence();
//                licence.setDateDebut(Instant.now());
//                licence.setTypeInstallation(li.getTypeInstallation());
//                licence.setCodeInstallation(li.getCodeInstallation());
//                String s = getHash(getLicenceService.getLicence(), resp.getBody().getCodeStatut(), licence.getDateDebut(),
//                        licence.getCodeInstallation(), licence.getTypeInstallation());
//                licence.setType(resp.getBody().getTypeLicence());
//                licence.setSignature(passwordEncoder.encode(s));
//                licence.setCle(getLicenceService.getLicence());
//                licence.setCodeClient(resp.getBody().getCodeClient());
//                licence.setDenominationClient(resp.getBody().getDenominationClient());
//                licence.setCodeProduit(resp.getBody().getCodeProduit());
//                licence.setCodeStatut(resp.getBody().getCodeStatut());
//                licence.setLibelleStatut(resp.getBody().getLibelleStatut());
//
//                licenceRepository.save(licence);
//
//            } else if (l.size() > 1) {
//                throw new LicenceRuntimeException(LICENCE_TABLE_ALTERED);
//            } else {
//                ResponseEntity<LicenceDto> res;
//                Licence c = l.get(0);
//                String s = getHash(getLicenceService.getLicence(), c.getCodeStatut(), c.getDateDebut(),
//                        c.getCodeInstallation(), c.getTypeInstallation());
//                if (!passwordEncoder.matches(s, c.getSignature())) {
//                    c.setCodeStatut(ETAT_EXPIRE);
//                    licenceRepository.save(c);
//                    throw new LicenceRuntimeException(LICENCE_TABLE_ALTERED);
//                }
//                try {
//                    res = restTemplate.exchange(licenceServerVerif + "?cle=" + getLicenceService.getLicence()
//                            + "&code=" + c.getCodeInstallation(), HttpMethod.GET, request, LicenceDto.class);
//
//                } catch (RestClientException e) {
//                    throw new LicenceRuntimeException(SERVER_OUT_OF_SERCICE + " 85296");
//
//                }
//                if (res.getStatusCode() != HttpStatus.OK) {
//                    throw new LicenceRuntimeException("Le serveur n'a pas pu valider la clé d'activation");
//
//                }
//                licenceRepository.delete(c.getSignature());
//                licenceRepository.flush();
//                c.setCodeStatut(res.getBody().getCodeStatut());
//                c.setLibelleStatut(res.getBody().getLibelleStatut());
//                s = getHash(getLicenceService.getLicence(), res.getBody().getCodeStatut(), c.getDateDebut(),
//                        c.getCodeInstallation(), c.getTypeInstallation());
//                c.setSignature(passwordEncoder.encode(s));
//                licenceRepository.save(c);
//            }
//
//        }
//    }
//
//    public boolean cleIsActivated() {
//        if (getLicenceService != null) {
//            List<Licence> l = licenceRepository.findAll();
//            if (!l.isEmpty()) {
//                if (getLicenceService.getLicence() == null || getLicenceService.getLicence().isEmpty()) {
//
//                    throw new LicenceRuntimeException("Veuillez activer ce produit");
//
//                }
//                if (l.size() > 1) {
//                    throw new LicenceRuntimeException("Les données de licence ont été altérée");
//
//                } else {
////                      System.out.println("===================== CLE IS ACTIVATED 3");
//                    Licence c = l.get(0);
//                    String s = getHash(getLicenceService.getLicence(), c.getCodeStatut(), c.getDateDebut(),
//                            c.getCodeInstallation(), c.getTypeInstallation());
////                      System.out.println("===================== CLE IS ACTIVATED 4" + s);
////                      System.out.println("===================== CLE IS ACTIVATED 5" + c.getSignature());
//                    if (!passwordEncoder.matches(s, c.getSignature())) {
////                         System.out.println("===================== CLE IS ACTIVATED 6" + c.getSignature());
//                        c.setCodeStatut(ETAT_EXPIRE);
//                        licenceRepository.save(c);
//                        throw new LicenceRuntimeException(LICENCE_TABLE_ALTERED);
//
//                    } else {
////                        System.out.println("===================== CLE IS ACTIVATED 7 " + c.getCodeStatut());
//                        if (c.getCodeStatut().equals(ETAT_EXPIRE)) {
////                             System.out.println("===================== CLE IS ACTIVATED 8 " + c.getCodeStatut());
//                            return false;
//                        }
//                    }
//
//                }
//            }
//
//        }
//        return true;
//    }
//
//    private String getHash(String cle, String statut, Instant dateDebut, String codeInstallation, String typeInstallation) {
//        String s = cle + "##NGS$$" + statut + "##GNS$$" + dateDebut.toEpochMilli() + "##TKI$$" + codeInstallation + "##TPI$$" + typeInstallation;
//        return s;
//
//    }
//
//}
