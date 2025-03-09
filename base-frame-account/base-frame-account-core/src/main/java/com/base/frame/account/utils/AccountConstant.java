/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.account.utils;

/**
 *
 * @author Alassani
 */
public class AccountConstant {

    /**
     * FONCTION PROFIL
     */
    public static final String FC_PROFIL = "FC_PROF";
    public static final String FC_PROFIL_LIRE = "FC_PROF.1";
    public static final String FC_PROFIL_SAISIR = "FC_PROF.2";

    /**
     * FONCTION UTILISATEUR
     */
    public static final String FC_USER = "FC_USER";
    public static final String FC_USER_LIRE = "FC_USER.1";
    public static final String FC_USER_SAISIR = "FC_USER.2";
    public static final String SCHEMA = "securite";

    /*
            BEGIN Mesages d'erreurs
     */
    public static final String MSG_ETAT_OBLIG = "socle.etat.oblig";

    public static final String MSG_ACCOUNT_PROFIL_CODE_OBLIG = "account.profil.code.oblig";
    public static final String MSG_ACCOUNT_PROFIL_CODE_USE = "account.profil.code.use";
    public static final String MSG_ACCOUNT_PROFIL_INTITULE_OBLIG = "account.profil.intitule.oblig";

    public static final String MSG_ACCOUNT_HABILITATION_PROFIL_OBLIG = "account.profil.habilitation.profil.oblig";
    public static final String MSG_ACCOUNT_HABILITATION_FONCTION_OBLIG = "account.profil.habilitation.fonction.oblig";
    public static final String MSG_ACCOUNT_HABILITATION_FONCTION_EXIST = "account.profil.habilitation.fonction.exist";
    public static final String MSG_ACCOUNT_HABILITATION_NIVEAU_OBLIG = "account.profil.habilitation.niveau.oblig";

    public static final String MSG_ACCOUNT_PROFIL_DELETE_CONTROLE_PROFILE_USE = "account.profil.delete.controle.profil.use";

//    USER EDIT
    public static final String MSG_ACCOUNT_USER_USERNAME_OBLIG = "account.user.username.oblig";
    public static final String MSG_ACCOUNT_USER_USERNAME_EXIST = "account.user.username.exist";
    public static final String MSG_ACCOUNT_USER_NOM_OBLIG = "account.user.nom.oblig";
    public static final String MSG_ACCOUNT_USER_PRENOM_OBLIG = "account.user.prenom.oblig";
    public static final String MSG_ACCOUNT_USER_EMAIL_OBLIG = "account.user.email.oblig";
    public static final String MSG_ACCOUNT_USER_EMAIL_EXIST = "account.user.email.exist";

//   USER PASSWORD EDIT
    public static final String MSG_ACCOUNT_PASSWORD_EDIT_USERNAME_OBLIG = "account.password.edit.user.oblig";
    public static final String MSG_ACCOUNT_PASSWORD_EDIT_PASS_OBLIG = "account.password.edit.pass.oblig";
    public static final String MSG_ACCOUNT_PASSWORD_EDIT_CONFIRM_PASS_OBLIG = "account.password.edit.confirmpass.oblig";
    public static final String MSG_ACCOUNT_PASSWORD_EDIT_CONTROLE_PASS_CONFORME = "account.password.edit.controle.conforme";

    public static final String MSG_ACCOUNT_PASSWORD_EDIT_OLD_PASS_OBLIG = "account.password.edit.oldpass.oblig";
    public static final String MSG_ACCOUNT_PASSWORD_EDIT_OLD_PASS_INCORRECT = "account.password.edit.oldpass.incorrect";

    //   USER PASSWORD REINIT
    public static final String MSG_ACCOUNT_PASSWORD_REINIT_INVALIDE = "account.password.reinit.invalide";
    public static final String MSG_ACCOUNT_PASSWORD_REINIT_EXPIRE = "account.password.reinit.expire";
    public static final String MSG_ACCOUNT_PASSWORD_REINIT_PASS_OBLIG = "account.password.reinit.pass.oblig";
    public static final String MSG_ACCOUNT_PASSWORD_REINIT_CONFIRM_PASS_OBLIG = "account.password.reinit.confirmpass.oblig";
    public static final String MSG_ACCOUNT_PASSWORD_REINIT_CONTROLE_PASS_CONFORME = "account.password.reinit.controle.conforme";

//    USER PASSWORD FORGET
    public static final String MSG_ACCOUNT_PASSWORD_FORGET_USER_INACTIF = "account.password.forget.user.inactif";
    public static final String MSG_ACCOUNT_PASSWORD_FORGET_EMAIL_INVALIDE = "account.password.forget.email.invalide";

//    ASSOCIATION UTILISATEUR PROFIL
    public static final String MSG_ACCOUNT_ASSOCIATION_USER_PROFIL_USER_OBLIG = "account.user.profil.user.oblig";
    public static final String MSG_ACCOUNT_ASSOCIATION_USER_PROFIL_PROFIL_OBLIG = "account.user.profil.profil.oblig";
    /*
            END Mesages d'erreurs
     */
    public static final String ERROR_404_FILE = "errors/404";
    public static final String FC_GROUPE_UTILISATEUR_URL = "parametre/groupe-utilisateur";
}
