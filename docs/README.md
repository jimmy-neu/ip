# ZhengjunChatbox (Instructor Iruka Edition) User Guide

![Product Screenshot](docs/Ui.png)

Welcome to the Hidden Leaf Training Grounds! ZhengjunChatbox is a desktop task-tracking application disguised as a ninja mission scroll. It is designed to help you manage your daily D-Rank missions, strict deadlines, and ongoing events through an immersive, anime-themed Graphical User Interface (GUI). Type your commands, and Instructor Iruka will keep your scroll organized!

---

## Adding deadlines

Adds a time-sensitive mission with a strict deadline to your training scroll.

Example: `deadline <description> /by <d/M/yyyy HHmm>`

Example: `deadline Submit Chunin Exam application /by 14/03/2026 1700`

Instructor Iruka will accept the mission, add it to your scroll, and tell you how many active missions you currently have.

Mission accepted. I've added this jutsu:
[D][ ] Submit Chunin Exam application (by: Mar 14 2026 5:00PM)
Now you have 1 missions in the scroll.


## Adding standard missions (ToDos)

Adds a standard D-Rank mission to your scroll without any specific date or time constraints.

Example: `todo <description>`

Example: `todo Master the Rasengan`

The chatbox will log the new jutsu and update your total mission count.

Mission accepted. I've added this jutsu:
[T][ ] Master the Rasengan
Now you have 2 missions in the scroll.


## Adding events

Logs an ongoing event or training session that has a specific start and end time.

Example: `event <description> /from <start time> /to <end time>`

Example: `event Bell Test /from Monday 0800 /to Monday 1200`

The system will record the event details and confirm the addition to your active list.

Mission accepted. I've added this jutsu:
[E][ ] Bell Test (from: Monday 0800 to: Monday 1200)
Now you have 3 missions in the scroll.


## Listing all missions

Displays all active and completed missions currently written in your scroll.

Example: `list`

Instructor Iruka will read out every task currently saved in your memory file.

Here are the active missions in your scroll:
1.[D][ ] Submit Chunin Exam application (by: Mar 14 2026 5:00PM)
2.[T][ ] Master the Rasengan
3.[E][ ] Bell Test (from: Monday 0800 to: Monday 1200)


## Marking a mission as done

Marks a specific mission as successfully accomplished, updating its status with an 'X'.

Example: `mark <mission_number>`

Example: `mark 2`

The bot will praise your success and visually update the task in the scroll.

Great job! Mission accomplished. I've marked this task as done:
[T][X] Master the Rasengan


## Unmarking a mission

Marks a previously completed mission as incomplete so you can continue training or working on it.

Example: `unmark <mission_number>`

Example: `unmark 2`

The bot will acknowledge the change and remove the 'X' from the task's status.

Understood. I've marked this mission as incomplete. Keep training:
[T][ ] Master the Rasengan


## Deleting a mission

Permanently erases a mission from your records and adjusts the list order.

Example: `delete <mission_number>`

Example: `delete 3`

The specified task will be removed, and you will receive a confirmation of the new total count.

Noted. I've removed this jutsu:
[E][ ] Bell Test (from: Monday 0800 to: Monday 1200)
Now you have 2 missions in the scroll.


## Finding a mission

Searches your archives and returns any missions containing the specific keyword you provide.

Example: `find <keyword>`

Example: `find Exam`

The system will filter your list and display only the tasks that match the keyword.

Here are the matching missions in your scroll:
1.[D][ ] Submit Chunin Exam application (by: Mar 14 2026 5:00PM)


## Getting a cheer

Outputs a random motivational quote to keep your ninja spirit high.

Example: `cheer`

The bot will read a random line from your `data/quotes.txt` file.

"A smile is the best way to get yourself out of a tight spot." - Sakura Haruno


## Exiting the application

Saves your scroll and safely exits the training grounds.

Example: `bye`

The bot will say farewell, and the application window will automatically close after 1.5 seconds.

Farewell, Naruto. Keep your training up!