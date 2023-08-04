BEGIN;
TRUNCATE personas;

INSERT INTO `personas` (`id`, `nombre`, `email`) VALUES
    (1, 'Juan', 'juan@gmail.com'),
    (2, 'Maria', 'maria@gmail.com'),
    (3, 'Rita', 'rita@gmail.com');

COMMIT;