
def load_level(filename):
    """
    Loads the level and returns an object of your choosing
    """

    try:
        f = open("%s" % filename, "r")
        level_file = read_level(f)
        f.close()
        is_levelcorrect,plant_name,plant_width,\
        plant_height,rover_cor,tiles=is_level_correct(level_file)
        if not is_levelcorrect:
            print("Ubable to load level file")
        return is_levelcorrect,plant_name,plant_width,plant_height,rover_cor,tiles
    except :
        print("\nLevel file could not be found")
##read file
def read_level(file):
    level_file=list()
    for line in file.readlines():
        line=line.replace("\n","")
        if line!="":
            level_file.append(line)
    return level_file

def is_level_correct(level_file):
    """

    :param level_file:
    :return: is_levelfile_correct, if level file is right
    plant_name, name of plant
    plant_width, width of planet
    plant_height, height of planet
    rover_cor, coordinates of rover
    tiles    all tiles
    """
    plant_name = ""
    plant_width = ""
    plant_height = ""
    rover_cor = list()
    tiles = list()
    is_levelfile_correct=True
    label_tiles = level_file.index("[tiles]")
    if label_tiles<5:
        is_levelfile_correct=False
    for i in range(1,label_tiles):
        elements=level_file[i].split(",")
        if elements[0]=="name":
            plant_name=elements[1]
        if elements[0]=="width" :
            plant_width=int(elements[1])
            if plant_width<5 :
                is_levelfile_correct=False
        if elements[0] == "height":
            plant_height=int(elements[1])
            if plant_height<5:
                is_levelfile_correct=False
        if elements[0]=="rover":
            rover_x=int(elements[1])
            rover_y=int(elements[2])
            if rover_x<0 or rover_y<0:
                is_levelfile_correct=False
            if rover_x>plant_width or rover_y>plant_height:
                is_levelfile_correct=False
            rover_cor.append(rover_x)
            rover_cor.append(rover_y)
    len_tiles=len(level_file)-label_tiles-1
    if len_tiles!=plant_width*plant_height:
        is_levelfile_correct=False
    else:
        for i in range(label_tiles+1,len(level_file)):
            element_tile=level_file[i].split(",")
            tiles.append(element_tile)
            if len(element_tile)==3:
                if int(element_tile[1])<=int(element_tile[2]):
                    is_levelfile_correct=False
    return is_levelfile_correct,plant_name,plant_width,plant_height,rover_cor,tiles
