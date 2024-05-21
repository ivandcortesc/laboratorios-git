# Labortaorio 4

## Scenario 1: Pseudo-Code for Authentication System
```
FUNCTION authenticateUser(username, password):
QUERY database WITH username AND password
IF found RETURN True
ELSE RETURN False
```
Issues de Seguridad: 
Password en Texto plano, esto es una vulverabilidad de seguridad ya que si un atacante gana acceso a la BD
puede facilmente tener acceso a los passwords.

Solucion: Implementar algun Hash y un algoritmo de Encripcion y desencripcion.
```
Function decryptBDPass (){
  Implement .. AES algorithm for decrypt
  }

FUNCTION authenticateUser(username, password):
 DECRYPT  decPass =  decryptBDPass(pawwsord);
QUERY database WITH username AND password
IF found RETURN True
ELSE RETURN False

```
Otro issue que se ve, es el SQL Injection, al ser un SQL plano, podria un atacante manipular y tener acceso a datos de nuestra base.

Solucion : Utilizar prepared statements para prevenir esta vulnerabilidad
```
PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE user = ? and password = ?");
pstmt.setString(1, userSuppliedEmail);
ResultSet results = pstmt.executeQuery();
```

Por ultimo en lugar de retornar un true or false, se recomendario regresar un token en caso de  que la autenticacion fue exitosa
Y un mesnaje de error controlado en caso de que fallo.

## Scenario 2: JWT Authentication Schema

```
DEFINE FUNCTION generateJWT(userCredentials):
IF validateCredentials(userCredentials):
SET tokenExpiration = currentTime + 3600 // Token expires in one hour
RETURN encrypt(userCredentials + tokenExpiration, secretKey)
ELSE:
RETURN error
```
Para robustecer la implementacion del token , es importante expirar y revalidar los token generados 
asi como integrar una funcion para refeshToken, que nos ayude a mantener  la sesion del usuario como valida y a su vez renovarla.
Agregar un algoritmo para poder 

```



public class JwtTokenGenerator {
private static final String SECRET = $ENV_VAR; // Este valor seria tomado de un Secrets Manager como el de AWS

    public String generateJWT(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET); // se implementa funcion de encripcion
            String token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("username", username)
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000)) // Token expires in one hour
                    .sign(algorithm);
            return token;
        } catch (UnsupportedEncodingException exception){
            //UTF-8 encoding not supported
            exception.printStackTrace();
            return null;
        } 
// implementacion de una Refresh token para refrescar la sesion del usuario
class RefreshToken(){
    
    Implement Refresh token logic...
    
}

class RevokeToken(){
    En caso de requerir revocar la sesion del usaario ya que a lo mejor por bussiness case, no se pueden tener mas de un usuario autenticado.
    Implement Revoque token logic...
    
}

public  validateCredentials(userCredentials){

Implementar metodo para decriptar el Hash de las credenciales

} 
`



## Scenario 3: Secure Data Communication Plan

```
PLAN secureDataCommunication:
  IMPLEMENT SSL/TLS for all data in transit
  USE encrypted storage solutions for data at rest
  ENSURE all data exchanges comply with HTTPS protocols

```
Se robustece la seguridad con este plan
```
PLAN secureDataCommunication:
  IMPLEMENT SSL/TLS Encryption for all data in transit
  USE apigee(front back integration ) or apigateway (api-api communication) with authenticathion and authorizathion roles by api 
  APPLY withelisting for only receive request from authorized external ip's
  USE encrypted storage features for data at rest
  ENSURE all data exchanges  with HTTPS protocols
  IMPLEMENT StackHawk tools like ina CICD pipeline for ensure quick fix security issues 
```
