db = db.getSiblingDB('db_findbar');

db.bar.insertOne(
    {
        "_id": ObjectId('62294eb9b9cc76b9e23309be'),
        "nome": "BarBA",
        "horario_abertura": ISODate("0001-01-01T10:00:00.000Z"),
        "horario_fechamento": ISODate("0001-01-01T22:00:00.000Z"),
        "endereco": {
            "logradouro": "Rua Lucas Lindo",
            "numero": "42",
            "complemento": "Apartamento 3 bloco A",
            "cep": "01139-001",
            "bairro": "Jardim Sao Luis",
            "cidade": "Sao Paulo",
            "estado": "SP"
        },
        "tipo": "PUB",
        "estilos_musicais": [
            "ROCK"
        ],
        "musica_ao_vivo": true,
        "avaliacao": 2.5,
        "comentarios": [
            {
                "id": "d425a05f-8551-4a95-a68d-f057950b4d00",
                "barId": "62294eb9b9cc76b9e23309be",
                "mensagem": "Esse bar é incrível!",
                "nota": 4,
                "likes": 3,
                "dislikes": 999
            }
        ],
        "tags": [
            {
                "id": "4daa2d09-25af-4c23-bbcb-6a5aeda14ef9",
                "value": "Comida mexicana"
            }
        ]
    }
);