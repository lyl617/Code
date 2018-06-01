import tile
class Planet:
    def __init__(self, name, width, height,tiles):
        """
        Initialise the planet object
        """
        self.name=name
        self.width=width
        self.height=height
        self.tiles=list()
        self.init_tiles(tiles)
    ##init tiles of planet
    def init_tiles(self,tiles):
        temp_width=0
        sub_tiles=list()
        for i in range(len(tiles)):
            if temp_width==self.width:
               self.tiles.append(sub_tiles)
               sub_tiles=list()
               temp_width=0
            type=tiles[i][0]
            elevation=tiles[i][1:]
            sub_tiles.append(tile.Tile(type,elevation))
            temp_width += 1
        self.tiles.append(sub_tiles)

	
	
	
	
	
