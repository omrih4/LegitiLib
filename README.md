# LegitiLib
A library mod for the Legitimoose server.

Represents a world on legitimoose.com
---
```java
public class World {
    private final String name;
    private final String description;
    private final String owner;
    private final int votes; // defaults to 0 for worlds part of an ongoing jam
    private final int visits; // defaults to 0 for worlds part of an ongoing jam
    private final String created;
    private final String uuid;
}
```
Get API Instance
---
```java
LegitiLibAPI.getInstance()
```
Returns `true` if the player is on legitimoose.com
---
```java
LegitiLibAPI#onLegitimoose()
```
Returns the legitimoose `World` the client is currently online in (may be null)
---
```java
LegitiLibAPI#getWorld()
```
