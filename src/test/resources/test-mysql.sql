BEGIN;
--TRUNCATE personas;
delete from recargas WHERE id IN ( SELECT id From recargas );
delete from personas WHERE id IN ( SELECT id From personas );

INSERT INTO `personas` (`id`, `nombre`, `email`) VALUES (1, 'Juan', 'juan@gmail.com');
INSERT INTO `personas` (`id`, `nombre`, `email`) VALUES (2, 'Maria', 'maria@gmail.com');
INSERT INTO `personas` (`id`, `nombre`, `email`) VALUES (3, 'Rita', 'rita@gmail.com');

INSERT INTO `recargas` (`id`, `celular`, `operador`, `valor`, `persona_entity`) VALUES (1, '3001234567', 'TIGO', 50000, 1);
INSERT INTO `recargas` (`id`, `celular`, `operador`, `valor`, `persona_entity`) VALUES (2, '3101234567', 'CLARO', 50000, 2);
INSERT INTO `recargas` (`id`, `celular`, `operador`, `valor`, `persona_entity`) VALUES (3, '3151234567', 'MOVISTAR', 50000, 3);

COMMIT;