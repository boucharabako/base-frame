
-- /**lES PARAMS**/
INSERT INTO  socle.socle_param_code(id,code,description) 
VALUES 
('PARAM_CODE_ETAT','PARAM_CODE_ETAT','Codification des états'),
('PARAM_CODE_NVX','PARAM_CODE_NVX','Codification des niveaux d'' habilitation')

ON CONFLICT (id) DO UPDATE 
SET
code= EXCLUDED.code,
description =EXCLUDED.description;


INSERT INTO  socle.socle_param_list(id,code,libelle,param_code) 
VALUES 
('PARAM_LIST_ETAT_AC','PARAM_LIST_ETAT_AC','Actif','PARAM_CODE_ETAT'),
('PARAM_LIST_ETAT_IN','PARAM_LIST_ETAT_IN','Inactif','PARAM_CODE_ETAT'),
('PARAM_NVX_LI','1','Lire','PARAM_CODE_NVX'),
('PARAM_NVX_SA','2','Saisir','PARAM_CODE_NVX'),
('PARAM_NVX_AP','3','Approuver','PARAM_CODE_NVX'),
('PARAM_NVX_VA','7','Valider','PARAM_CODE_NVX'),
('PARAM_NVX_AD','8','Administrer','PARAM_CODE_NVX')
ON CONFLICT (id) DO UPDATE 
SET
code= EXCLUDED.code,
libelle= EXCLUDED.libelle,
param_code =EXCLUDED.param_code;



