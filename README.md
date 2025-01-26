# Simulación de Carrera en Java con Hilos

Este proyecto implementa una simulación de carrera utilizando **hilos** en Java. La simulación permite configurar una carrera con varios elementos (como coches, animales o personas) que avanzan a diferentes velocidades. La interfaz gráfica muestra el progreso de cada participante y al final se anuncia al ganador.

## Descripción

La simulación consiste en una carrera entre cuatro participantes (pueden ser coches, animales, personas, etc.). Cada participante tiene una velocidad diferente y el usuario puede configurar la distancia del circuito. A medida que la carrera avanza, se muestra el progreso de cada uno en la interfaz gráfica mediante barras de progreso.

Al finalizar la carrera, el participante que haya recorrido la mayor distancia es declarado ganador. Los demás participantes se detendrán y se mostrará cuánta distancia han recorrido.

## Características

- **Configuración del circuito**: Se puede ajustar la distancia de la carrera a través de la interfaz.
- **Carrera con cuatro participantes**: La carrera involucra cuatro elementos (coches, animales, personas, etc.), cada uno con su propia velocidad.
- **Simulación visual**: Durante la carrera, el progreso de cada participante se muestra mediante barras de progreso.
- **Anuncio de ganador**: Al final de la carrera, el participante que haya recorrido la mayor distancia es el ganador. Los demás participantes se detienen y se muestra la distancia recorrida por cada uno.

## Requisitos

- **Java 8** o superior.
- Interfaz gráfica utilizando **Swing** o **JavaFX**.
- Conocimientos básicos de **hilos** en Java para la simulación del movimiento de los participantes.

## Estructura del Proyecto

- **Carrera.java**: Lógica principal de la carrera, maneja la simulación de los participantes y la determinación del ganador.
- **Participante.java**: Clase que representa a cada participante en la carrera, con atributos como velocidad y distancia recorrida.
- **InterfazGrafica.java**: Interfaz gráfica que muestra las barras de progreso y permite la interacción con el usuario.


![image](https://github.com/user-attachments/assets/e3a02880-3c2e-4155-8420-3be46b63afb2)
