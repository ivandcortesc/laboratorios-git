# Evidencia Laboratorio 3

## 1. Scenario 1: User Authentication Tests

El error aqui es que las pruebas  estan genericas en cuanto al escenario y  no se tiene la correcta separacion de las mismas.


```typescript
TEST UserAuthentication
ASSERT_TRUE(authenticate("validUser", "validPass"), "Should succeed with correct credentials")
ASSERT_FALSE(authenticate("validUser", "wrongPass"), "Should fail with wrong credentials")
END TEST
```

Refactorizacion/Solucion:
Si separamos los test cases por cada una de las pruebas asi tenemos un mejor control sobre los resultados de cada una de las pruebas.

```typescript
TEST UserAuthentication_Success
  ASSERT_TRUE(authenticate("validUser", "validPass"), "Should succeed with correct credentials")
END TEST

TEST UserAuthentication_Fail
  ASSERT_FALSE(authenticate("validUser", "wrongPass"), "Should fail with wrong credentials")
END TEST
```


## Escenario 2:  Data Processing Functions

El error en este escenario es que se esta mezclando la prueba de exito y la prueba de error en un solo test, lo que hace que no se tenga un control adecuado de los resultados.

Ejemplo:

```typescript
TEST DataProcessing
    // Datos genericos creados para cubrir dos test cases
    DATA data = fetchData()
  TRY
    processData(data)
    ASSERT_TRUE(data.processedSuccessfully, "Data should be processed successfully")
  CATCH error
    ASSERT_EQUALS("Data processing error", error.message, "Should handle processing errors")
  END TRY
END TEST
```

Solución:

Para solucionar este tema, se dividen las pruebas, El primer test case valida la parte exitosa y el segundo escenario valida el posible caso de error  
Otra buena practica es generar datos de prueba para satisfacer cada uno de los escenarios por ejemplo con la funcion fetchDataError(), son datos de prueba para el escenario de Error.
Tambien se puede ser mas explicito en el manejo de las pruebas de error.

```typescript
TEST DataProcessing_successfully_200
    DATA data = fetchDataSuccess() // data created for success test case
    processData(data)
    ASSERT_TRUE(data.processedSuccessfully, "Data should be processed successfully")
END TEST

TEST DataProcessing_WithErrors_500
  DATA data = fetchDataError() // data created for error test case
  TRY
    processData(data)
  CATCH error
    ASSERT_EQUALS("Data processing error", error.message, "Should handle processing errors")
  END TRY
END TEST
```

# Escenario 3: UI Responsiveness

Ejemplo

En este caso de prueba "hardcoded" se esta pasando un valor fijo para la prueba, lo que no permite que se pueda probar con diferentes valores.     
```typescript
TEST UIResponsiveness
  UI_COMPONENT uiComponent = setupUIComponent(1024)
  ASSERT_TRUE(uiComponent.adjustsToScreenSize(1024), "UI should adjust to width of 1024 pixels")
END TEST

```

Solución:

Se deben integrar valores dinamicos para que se pueda probar con diferentes valores y asi tener una mejor cobertura de pruebas.

Ejemplo

```typescript
// test case with dinamic values
TEST UIResponsiveness (InitVal, changeVal)
  UI_COMPONENT uiComponent = setupUIComponent(InitVal)
  ASSERT_TRUE(uiComponent.adjustsToScreenSize(changeVal), `UI should adjust to width of ${changeVal} pixels`)
END TEST

```