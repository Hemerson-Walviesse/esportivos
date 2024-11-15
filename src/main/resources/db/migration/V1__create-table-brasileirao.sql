CREATE TABLE IF NOT EXISTS brasileirao (
                                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           jogo_id INT NOT NULL,
                                           name_league VARCHAR(100),
    data VARCHAR(50),
    local VARCHAR(100),
    arbitro VARCHAR(100),
    time_casa VARCHAR(100),
    time_visitante VARCHAR(100),
    placar_casa INT,
    placar_visitante INT,
    status VARCHAR(50),
    data_ultima_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );
