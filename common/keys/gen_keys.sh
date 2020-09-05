# https://en.wikibooks.org/wiki/Cryptography/Generate_a_keypair_using_OpenSSL
openssl genpkey -algorithm RSA -out privateKey.pem -pkeyopt rsa_keygen_bits:2048
openssl rsa -pubout -in privateKey.pem -out publicKey.pem