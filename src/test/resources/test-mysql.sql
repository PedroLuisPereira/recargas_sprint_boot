BEGIN;
--TRUNCATE personas;
delete from recargas WHERE id IN ( SELECT id From recargas );
delete from personas WHERE id IN ( SELECT id From personas );

INSERT INTO `personas` (`id`, `nombre`, `email`) VALUES (1, 'Juan', 'juan@gmail.com');
INSERT INTO `personas` (`id`, `nombre`, `email`) VALUES (2, 'Maria', 'maria@gmail.com');
INSERT INTO `personas` (`id`, `nombre`, `email`) VALUES (3, 'Rita', 'rita@gmail.com');

COMMIT;