# Battleship


> Implementación en Java del clásico juego **Battleship**, desarrollada como proyecto académico aplicando principios de **Programación Orientada a Objetos (POO)** y una arquitectura basada en el patrón **Modelo-Vista-Controlador (MVC)**.

---

##  Descripción

Battleship es un juego de estrategia por turnos en el que dos jugadores intentan localizar y hundir la flota enemiga. En esta versión, el usuario compite contra una inteligencia artificial controlada por el sistema.

El proyecto fue diseñado con énfasis en la modularidad, encapsulación y separación de responsabilidades, permitiendo una estructura clara y mantenible del código.

---

##  Características:

-  Gestión de distintos tipos de barcos.
-  Sistema de disparos por coordenadas.
-  Oponente controlado por la computadora.
-  Modo de configuración personalizado.
-  Registro y visualización del estado del tablero.
-  Control del flujo de turnos.
-  Detección automática del ganador.

---

##  Arquitectura del proyecto

El proyecto sigue una estructura inspirada en el patrón **MVC (Model-View-Controller)**.

```text
src/
├─ model/
│  ├─ Coordinate.java
│  ├─ Player.java
│  ├─ Ship.java
│  ├─ ShipType.java
│  └─ Board.java
├─ controller/
│  └─ Controller.java
└─ ui/
   └─ Main.java
```

### Responsabilidades principales

| Componente | Responsabilidad |
|------------|------------------|
| `Controller` | Coordina la lógica general del juego y el flujo de ejecución. |
| `Player` | Representa a cada participante de la partida. |
| `Ship` | Modela los barcos y su estado durante el juego. |
| `ShipType` | Define los diferentes tipos de barcos disponibles. |
| `Coordinate` | Gestiona las posiciones dentro del tablero. |
| `Table` | Administra la información y operaciones del tablero. |
| `Main` | Maneja la interacción con el usuario desde la consola. |

---

## 🛠️ Tecnologías utilizadas

- **Java**
- Programación Orientada a Objetos (POO)
- Arquitectura MVC
- Git y GitHub para control de versiones

---

##  Ejecución

1. Clona el repositorio:

```bash
git clone https://github.com/TU_USUARIO/battleship2-JuanDiegoDominguez16.git
```

2. Accede al directorio del proyecto:

```bash
cd battleship2-JuanDiegoDominguez16
```

3. Compila los archivos Java:

```bash
javac src/**/*.java
```

4. Ejecuta la aplicación:

```bash
java ui.Main
```

---



---

##  Contexto académico

Este proyecto fue desarrollado como parte de una actividad formativa enfocada en fortalecer habilidades relacionadas con:

- Diseño orientado a objetos.
- Modelado de problemas mediante clases y objetos.
- Implementación de estructuras de control.
- Organización y mantenimiento de proyectos en Java.
- Uso de sistemas de control de versiones.

---

##  Autor

**Juan Diego Domínguez**

GitHub: https://github.com/JuanDiegoDominguez16

---

##  Licencia

Este proyecto tiene fines educativos y de aprendizaje.
