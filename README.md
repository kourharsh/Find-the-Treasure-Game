# Find-the-Treasure-Game
Console application to discover the path for the treasure for a map of tunnels stored in a text file.

## Description 
Starting from the top left corner of the map, application will find the path towards the treasure. All the paths tried are indicated by "+" character and final path is indicated by "!" character. If there is no path found till the treasure, application will indicate a message indicating no path found.

## Input Map: 
![picture alt](https://github.com/kourharsh/Find-the-Treasure-Game/blob/master/input_map.png "Title is optional")

"–" character indicates a free path in this direction.
"#" character indicates the blocked path.
"@" character indicates the location of the treasure.
The search will start at the top.

## Output Map:
![picture alt](https://github.com/kourharsh/Find-the-Treasure-Game/blob/master/output_map.png "Title is optional")

Indicates success or failure if the treasure was found or not.
The map will been updated to indicate how the walk was done.
"+" characters indicate the path that led to the treasure.
"!" characters indicate the tried tunnels but could not to lead to the treasure.

## Output when there is no path found for The treasure:

![picture alt](https://github.com/kourharsh/Find-the-Treasure-Game/blob/master/No_Treasure_output.png "Title is optional")
