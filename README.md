<!-- 1.0.3-b1 -->
# 99.7% Citric Liquid

The project consists in creating a (simplified) clone of the game **100% Orange Juice**
developed by [Orange_Juice](http://daidai.moo.jp) and distributed by
[Fruitbat Factory](https://fruitbatfactory.com).

### Comparativa código base
Modifiqué la jerarquía de clases, implementando una clase abstracta para representar
las características comunes que comparten tanto los players como ambos tipos de rivales.
De dicha clase abstracta heredan las clases `Player`,  `WildUnit` y `BossUnit`.

Eliminé el método `activatedBy()` de la clase `Panel`, pues llegaba a incoherencias con 
las funcionalidades de los distintos tipos de paneles, en consiguiente decidí crear subclases para cada panel, siendo `Panel`
 la que representa al tipo neutral y todas las demás heredan de esta última. Acá podemos destacar a `EncounterPanel` y 
a `BossPanel`, ambas cuentan con una variable que representa al rival que enfrentan los jugadores que caen en dicho panel, además del método `shortBattle()` 
en que se desarrolla una batalla entre un player con el rival correspondiente al panel. Muy relacionado a lo anterior, separé `PanelTest` en subclases para ser consecuente en como había hecho la diferenciación de paneles.

También agregué algunos métodos como el de `normaCheck()` y algunas variables como las `wins` para los players.
### Funcionamiento
En un principio había decidido crear más de una interfaz para realizar la batalla entre personajes, 
ya que, por ejemplo, un wild unit no tendría que atacar a un boss unit, pero ese caso nunca ocurre, pues cuando se implemente el controlador del juego ese caso 
no se intentaría llevar a cabo.

Apliqué double dispatch para lograr que una batalla pudiera ocurrir entre dos personajes. Ocupé herencia de clases para representar 
comportamientos comunes de ciertos personajes, por ejemplo los stats de un player y los stats de un boss unit. Usé clases del tipo enum para modelar a los tres wild units y los 
tres boss units.
### Consideraciones

- Como están documentados los métodos de `attack()` y los del estilo `receiveAttack()` en `Ibattle`, no los volví a documentar al 
reescribirlos en las clases que implementan la interfaz.

- Falta implementar que los jugadores puedan escoger entre defender y esquivar.
- El tipo de batalla implementado es sumamente simple, solo considera que el parámetro `damage` del método `receiveDamage()` corresponde
al `atk` del jugador que está atacando, en un escenario real habría que ajustar `damage` según la decisión del otro jugador (defender o evadir).

- La variable `playersHere` en la clase `Panel`, será de utilidad en una simulación del juego para
saber los jugadores que están parados en dicho panel.

