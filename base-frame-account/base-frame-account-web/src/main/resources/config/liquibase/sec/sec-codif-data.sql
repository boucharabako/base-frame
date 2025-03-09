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
('PARAM_INDICE_COMMENT','PARAM_INDICE_COMMENT','Indice comment')


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
('141.1.0.0','141.1.0.0','Parametre Indice de commentaire','PARAM_INDICE_COMMENT')
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




