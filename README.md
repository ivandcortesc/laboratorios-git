# Evidencia Laboratorio 1

## 1. Objetivos  Branch Strategy Simulation

Actualizo el archivo con los cambios a modificar
![img.png](img.png)

Pongo en stage los cambios

![img_1.png](img_1.png)

Hago un commit con los cambios
![img_3.png](img_3.png)
Intento subir  cambios pero  hay cambios en el remoto que no tengo en local. 
![img_4.png](img_4.png)
Hago un pull para traer los cambios del remoto y los mando al remoto
![img_5.png](img_5.png)
Evidencia de cambios en main en remoto
![img_6.png](img_6.png)

## GitFlow
Se crea rama develop

![img_7.png](img_7.png)

creo la rama develop
C:\desarrollo\workspace\ironhack\laboratorios-git>git checkout -b develop
Switched to a new branch 'develop'

Creo  el branch feature-lab-1 comp copia de develop
C:\desarrollo\workspace\ironhack\laboratorios-git>git checkout -b feature-lab-1 develop
Switched to a new branch 'feature-lab-1'

Modifique los archivos ReadME.md y agrego una imagen img_7.png

![img_8.png](img_8.png)

Agrego los archivos a stage , hago commit a la feature branch, me cambio a develop y hago merge de los cmabios.

![img_9.png](img_9.png)

![img_10.png](img_10.png)

![img_11.png](img_11.png)
Continuo con el flujo de mergear mis cambios a develop y luego a main
![img_12.png](img_12.png)

## 3.- Resolucion de conflictos

Creo la rama para resolver conflictos

![img_15.png](img_15.png)

![img_16.png](img_16.png)

![img_17.png](img_17.png)

![img_18.png](img_18.png)
Se da el clinflicto entre ramas
![img_20.png](img_20.png)

Estos son los cambios a realizar por rama
![img_21.png](img_21.png)
Decido quedarme con los cambios de la rama de conflictos ya que son los correctos
![img_22.png](img_22.png)

Guardo los cambios para el mensaje de merge del commit

![img_23.png](img_23.png)

Pasos previos a la ventana para confirmar el commit
![img_24.png](img_24.png)

##4.- PR
Creo PR  para emparejar develop ya que se quedo atras de main, entonces creo el branch feature-pr
![img_25.png](img_25.png)

![img_26.png](img_26.png)

PR Aprobado 
![img_27.png](img_27.png)

Merge del PR
![img_28.png](img_28.png)