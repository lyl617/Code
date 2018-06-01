
class Tile:
		
    def __init__(self,type,elevation):
        """
        Initialises the terrain tile and attributes
        """
        self.type=type
        self.elevation=elevation
        self.isexplored=False
        self.occupant=None

    def elevation(self):
        """
        Returns an integer value of the elevation number
        of the terrain object
        """
        int_ele=list()
        int_ele.append(int(self.elevation[0]))
        if len(self.elevation)==2:
            int_ele.append(int(self.elevation[1]))
        return int_ele

    def is_shaded(self):
        """
        Returns True if the terrain tile is shaded, otherwise False
        """
        if self.type=="plains":
           return False
        else:
            return True

    def set_occupant(self, obj):
        """
        Sets the occupant on the terrain tile
        """
        self.occupant=obj
    def get_occupant(self):
        """
        Gets the entity on the terrain tile
        If nothing is on this tile, it should return None
        """
        return self.occupant
	
	
