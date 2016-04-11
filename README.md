# Scarne's-Dice


		Scarne's Dice is a turn-based dice game where players score points by rolling a die and then:
		- If they roll a 1, score no point and lose their turn
		- If they roll a 2 to 6:
		- Add the rolled value to their points
		- Choose to either reroll or keep their score and end their turn

		The winner is the first player that reaches (or exceeds) 100 points.



### Example


		If a player starts their turn and rolls a 6, they can choose to either ‘hold’ and
		end their turn, in which case they can add the 6 to their score, or to reroll and
		potentially score more points.

		Let’s say they decide to roll again, and they get a 4. They now have the option
		to end their turn and add 10 points (6 + 4) to their score, or to roll again to
		get even more points.

		They decide to roll again, but get a 1. Getting a 1 makes the player lose all the
		points from their turn (so their score is the same as before their turn), and
		finishes their turn, allowing the second player to begin their turn.

		This goes on until one of the players reaches 100 points or more.

### Project description:

		- A TextView to display the score and status of the game
		- An ImageView to display the current die (with default of dice 1)
		- Three buttons to either roll the die, end your turn, or start over
