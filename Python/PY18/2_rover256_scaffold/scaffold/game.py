import loader
import planet
import rover
def quit():
	"""
	Will quit the program
	"""
	exit()
	
def menu_help():
	"""
	Displays the help menu of the game
	"""
	print("START <level file> - Starts the game with a provided file.\n"
		  "QUIT - Quits the game\n"
		  "HELP - Shows this message")


def menu_start_game(filepath):
    """
    Will start the game with the given file path
    """
    is_levelcorrect,name,width,height,rover_cor,tiles=loader.load_level(filepath)
    if is_levelcorrect:
        plant=planet.Planet(name,width,height,tiles)
        rov=rover.Rover(rover_cor,plant)
        while not rov.isfinish:
            user_behavior = input(
                "Please choose what you want to do:\n1.SCAN <type>\n2.MOVE <direction> <sycles>\n3.WAIT <cycles>\n4.FINISH")
            # user_behavior="MOVE  10"
            input_elements = user_behavior.split(" ")
            if user_behavior.startswith("SCAN"):
                # input_elements=user_behavior.split(" ")
                if len(input_elements)==2:
                   rov.scan(input_elements[1])
                else:
                    print("Please input right command!")
            elif user_behavior.startswith("MOVE"):
                if len(input_elements) == 3:
                    rov.move(input_elements[1], input_elements[2])
                    rov.stats()
                else:
                    print("Please input right command!")
            elif user_behavior.startswith("WAIT"):
                if len(input_elements) == 2:
                    rov.wait(input_elements[1])
                    rov.stats()
                else:
                    print("Please input right command!")
            elif user_behavior.startswith("FINISH"):
                if len(input_elements)==1:
                    rov.finish()
                else:
                    print("Please input right command!")
            else:
                print("Please input right command!")


def menu():
    """
    Start the menu component of the game
    """
    menu_help()
    user_option=input("Please select the item:")
    if user_option.startswith("START"):
        arguments=user_option.split(" ")
        menu_start_game(arguments[1])
    elif user_option.startswith("QUIT"):
        quit()
    elif user_option.startswith("HELP"):
        menu()
    else:
        print("No menu item")

menu()
