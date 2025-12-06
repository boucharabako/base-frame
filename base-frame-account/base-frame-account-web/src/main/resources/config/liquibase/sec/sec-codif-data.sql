/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Alassani
 * Created: 18 nov. 2022
 */

INSERT INTO  socle.socle_param_code(id,code,description) 
VALUES 
('PARAM_TYPE_CODIF','PARAM_TYPE_CODIF','Codification des type codif'),
('PARAM_PORT','PARAM_PORT','Codification du port'),
('PARAM_DOMAINE','PARAM_DOMAINE','Codification du domaine'),
('PARAM_STATUT','PARAM_STATUT','Codification du Statut Actif 20'),
('PARAM_BOOLEAN_TRUE','PARAM_BOOLEAN_TRUE','Codification du Statu Vrai'),
('PARAM_BOOLEAN_FALSE','PARAM_BOOLEAN_FALSE','Codification du Statu Faux'),
('PARAM_WKFL_PROFIL.1','PARAM_WKFL_PROFIL.1','Workflow Profil'),
('PARAM_WKFL_UTILISATEUR.1','PARAM_WKFL_UTILISATEUR.1','Workflow Utilisateur'),

('PARAM_ACTIVER_PROFIL_1.11','PARAM_ACTIVER_PROFIL_1.11','Activer Profil'),
('PARAM_ABANDONNER_PROFIL_1.12','PARAM_ABANDONNER_PROFIL_1.12','Abandonné Profil'),

('PARAM_ACTIVER_UTILISATEUR_1.11','PARAM_ACTIVER_UTILISATEUR_1.11','Activer Utilisateur'),
('PARAM_ABANDONNER_UTILISATEUR_1.12','PARAM_ABANDONNER_UTILISATEUR_1.12','Abandonné Utilisateur'),

('PARAM_NIVEAU_HABILITATION','PARAM_NIVEAU_HABILITATION','Niveau d''habilitation'),
('PARAM_INDICE_COMMENT','PARAM_INDICE_COMMENT','Indice comment'),
('SEXE','SEXE','Sexe'),
('GROUPE_SANGUIN','GROUPE_SANGUIN','Groupe sanguin'),
('SPECIALITE','SPECIALITE','Spécialité médicale'),
('TYPE_ETABLISSEMENT','TYPE_ETABLISSEMENT','Type d''établissement'),
('REGION','REGION','Région')


ON CONFLICT (id) DO UPDATE
SET
code= EXCLUDED.code,
description =EXCLUDED.description;

INSERT INTO  socle.socle_param_list(id,code,libelle,param_code) 
VALUES 
('6','6','Parametre Type Codif','PARAM_TYPE_CODIF'),
('1','11','Parametre Port','PARAM_PORT'),
('12','12','Parametre Domaine','PARAM_DOMAINE'),
('20','20','Parametre Statut Actif','PARAM_STATUT'),
('4','4','Valeur vrai','PARAM_BOOLEAN_TRUE'),
('5','5','Valeur faux','PARAM_BOOLEAN_FALSE'),
 ('WKFL_PROFIL.1','WKFL_PROFIL.1','Workflow profils','PARAM_WKFL_PROFIL.1'),
('WKFL_UTILISATEUR.1','WKFL_UTILISATEUR.1','Workflow utilisateurs','PARAM_WKFL_UTILISATEUR.1'),

('PROFIL_1.11','PROFIL_1.11','Activer','PARAM_ACTIVER_PROFIL_1.11'),
('PROFIL_1.12','PROFIL_1.12','Abandonné','PARAM_ABANDONNER_PROFIL_1.12'),

('UTILISATEUR_1.11','UTILISATEUR_1.11','Activer','PARAM_ACTIVER_UTILISATEUR_1.11'),
('UTILISATEUR_1.12','UTILISATEUR_1.12','Abandonné','PARAM_ABANDONNER_UTILISATEUR_1.12'),

('30','30','Parametre niveau habilitation','PARAM_NIVEAU_HABILITATION'),
('141.1.0.0','141.1.0.0','Parametre Indice de commentaire','PARAM_INDICE_COMMENT'),
('SEXE.1','SEXE.1','Masculin','SEXE'),
('SEXE.2','SEXE.2','Féminin','SEXE'),
('GROUPE_SANGUIN.1','GROUPE_SANGUIN.1','A+','GROUPE_SANGUIN'),
('GROUPE_SANGUIN.2','GROUPE_SANGUIN.2','A-','GROUPE_SANGUIN'),
('GROUPE_SANGUIN.3','GROUPE_SANGUIN.3','B+','GROUPE_SANGUIN'),
('GROUPE_SANGUIN.4','GROUPE_SANGUIN.4','B-','GROUPE_SANGUIN'),
('GROUPE_SANGUIN.5','GROUPE_SANGUIN.5','0+','GROUPE_SANGUIN'),
('GROUPE_SANGUIN.6','GROUPE_SANGUIN.6','O-','GROUPE_SANGUIN'),
('GROUPE_SANGUIN.7','GROUPE_SANGUIN.7','AB+','GROUPE_SANGUIN'),
('GROUPE_SANGUIN.8','GROUPE_SANGUIN.8','AB-','GROUPE_SANGUIN'),
('SPECIALITE.1','SPECIALITE.1','Médecine générale','SPECIALITE'),
('SPECIALITE.2','SPECIALITE.2','Pédiatrie','SPECIALITE'),
('SPECIALITE.3','SPECIALITE.3','Gynécologie-Obstétrique','SPECIALITE'),
('SPECIALITE.4','SPECIALITE.4','Cardiologie','SPECIALITE'),
('SPECIALITE.5','SPECIALITE.5','Dermatologie','SPECIALITE'),
('SPECIALITE.6','SPECIALITE.6','Ophtalmologie','SPECIALITE'),
('SPECIALITE.7','SPECIALITE.7','ORL (Oto-Rhino-Laryngologie)','SPECIALITE'),
('SPECIALITE.8','SPECIALITE.8','Chirurgie générale','SPECIALITE'),
('SPECIALITE.9','SPECIALITE.9','Orthopédie','SPECIALITE'),
('SPECIALITE.10','SPECIALITE.10','Neurologie','SPECIALITE'),
('SPECIALITE.11','SPECIALITE.11','Psychiatrie','SPECIALITE'),
('SPECIALITE.12','SPECIALITE.12','Radiologie','SPECIALITE'),
('SPECIALITE.13','SPECIALITE.13','Anesthésie-Réanimation','SPECIALITE'),
('SPECIALITE.14','SPECIALITE.14','Médecine interne','SPECIALITE'),
('SPECIALITE.15','SPECIALITE.15','Pneumologie','SPECIALITE'),
('SPECIALITE.16','SPECIALITE.16','Gastro-entérologie','SPECIALITE'),
('SPECIALITE.17','SPECIALITE.17','Néphrologie','SPECIALITE'),
('SPECIALITE.18','SPECIALITE.18','Endocrinologie','SPECIALITE'),
('SPECIALITE.19','SPECIALITE.19','Rhumatologie','SPECIALITE'),
('SPECIALITE.20','SPECIALITE.20','Urologie','SPECIALITE'),
('SPECIALITE.21','SPECIALITE.21','Hématologie','SPECIALITE'),
('SPECIALITE.22','SPECIALITE.22','Oncologie','SPECIALITE'),
('SPECIALITE.23','SPECIALITE.23','Infectiologie','SPECIALITE'),
('SPECIALITE.24','SPECIALITE.24','Sage-femme','SPECIALITE'),
('SPECIALITE.25','SPECIALITE.25','Infirmier(ère)','SPECIALITE'),
('SPECIALITE.26','SPECIALITE.26','Pharmacien(ne)','SPECIALITE'),
('SPECIALITE.27','SPECIALITE.27','Kinésithérapeute','SPECIALITE'),
('SPECIALITE.28','SPECIALITE.28','Dentiste','SPECIALITE'),
('TYPE_ETABLIS.1','TYPE_ETABLIS.1','Poste de santé','TYPE_ETABLISSEMENT'),
('TYPE_ETABLIS.2','TYPE_ETABLIS.2','Centre de santé','TYPE_ETABLISSEMENT'),
('TYPE_ETABLIS.3','TYPE_ETABLIS.3','Centre medico-social','TYPE_ETABLISSEMENT'),
('TYPE_ETABLIS.4','TYPE_ETABLIS.4','Centre hospitalier Préfectoral','TYPE_ETABLISSEMENT'),
('TYPE_ETABLIS.5','TYPE_ETABLIS.5','Centre hospitalier Régional','TYPE_ETABLISSEMENT'),
('TYPE_ETABLIS.7','TYPE_ETABLIS.7','Centre hospitalier Universitaire','TYPE_ETABLISSEMENT'),
('TYPE_ETABLIS.8','TYPE_ETABLIS.8','Clinique privée','TYPE_ETABLISSEMENT'),
('TYPE_ETABLIS.9','TYPE_ETABLIS.9','Polyclinique ','TYPE_ETABLISSEMENT'),
('TYPE_ETABLIS.10','TYPE_ETABLIS.10','Laboratoire d''analyse médicale','TYPE_ETABLISSEMENT'),
('TYPE_ETABLIS.11','TYPE_ETABLIS.11','Centre de santé confessionnel','TYPE_ETABLISSEMENT'),
('TYPE_ETABLIS.12','TYPE_ETABLIS.12','Centre spécialisé','TYPE_ETABLISSEMENT'),
('REGION.1','REGION.1','Région maritime','REGION'),
('REGION.2','REGION.2','Région des plateaux','REGION'),
('REGION.3','REGION.3','Région Centrale','REGION'),
('REGION.4','REGION.4','Région de la Kara','REGION'),
('REGION.5','REGION.5','Région des savanes','REGION'),
('REGION.6','REGION.6','Grand-Lomé','REGION')

-- ('PARAM_NVX_LI','1','Lire','PARAM_CODE_NVX'),
-- ('PARAM_NVX_SA','2','Saisir','PARAM_CODE_NVX')
ON CONFLICT (id) DO UPDATE
SET
code= EXCLUDED.code,
libelle= EXCLUDED.libelle,
param_code =EXCLUDED.param_code;

-- /**lES PARAMS**/

/** LES CODIFICATIONS **/
INSERT INTO  socle.spr_codifications(code_codif,libelle,version,created_by,last_modified_by,porte,type_codif,domaine,code_statut) 
VALUES
('WKFL_PROFIL','Workflow des profils','1','SYSTEM','SYSTEM','1','6','12','20'),
('WKFL_UTILISATEUR','Workflow des utilisateurs','1','SYSTEM','SYSTEM','1','6','12','20'),
('WKFL_GROUPE','Workflow des groupes','1','SYSTEM','SYSTEM','1','6','12','20')


ON CONFLICT (code_codif) DO UPDATE 
SET
libelle= EXCLUDED.libelle,
porte= EXCLUDED.porte,
type_codif= EXCLUDED.type_codif,
domaine= EXCLUDED.domaine,
code_statut= EXCLUDED.code_statut,
version =EXCLUDED.version;


INSERT INTO  socle.socle_param_code(id,code,description) 
VALUES 
('WKFL_PROFIL.1','WKFL_PROFIL.1','Profils'),
('PROFIL_1.11','PROFIL_1.11','Activer'),
('PROFIL_1.12','PROFIL_1.12','Abandonné'),

('WKFL_UTILISATEUR.1','WKFL_UTILISATEUR.1','Utilisateurs'),
('UTILISATEUR_1.11','UTILISATEUR_1.11','Activer'),
('UTILISATEUR_1.12','UTILISATEUR_1.12','Abandonné')


ON CONFLICT (id) DO UPDATE 
SET
code= EXCLUDED.code,
description =EXCLUDED.description;




