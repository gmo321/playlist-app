# CPSC 210 Personal Project

## Playlist Application

This is a **Playlist Application** that allows users to create a playlist of songs. 
Some features will include:
 - adding songs
 - removing songs
 - displaying a list of the songs
 - playing the next song
 - playing previous song
 - replaying current song
 
This application will be useful for music lovers who love creating playlists of their favourite songs or songs of 
similar genres.
I wanted to make a Playlist application because I love listening to music for different types of tasks such as studying,
working or going on a walk. I feel that creating playlists are helpful for setting a tone for what type of task you are
about to accomplish!

## User Stories
- As a user, I want to be able to add a song to my playlist
- As a user, I want to be able to remove a song from my playlist
- As a user, I want to be able to view the list of songs on my playlist
- As a user, I want to be able to play the next song of my playlist
- As a user, I want to be able to play the previous song of my playlist
- As a user, I want to be able to replay the current song of my playlist
- As a user, I want to be able to save my playlist to file
- As a user, I want to be able to load my playlist from file
- As a user, I want to be able to shuffle the songs in my playlist

## Phase 4: Task 2
- I tested and designed Playlist and PlaylistApp classes to be robust. 
- The methods involved were:
- In Playlist class: addSong(), removeSong(), playNext(), playPrev(), replay(), getCurrentSong()
- In PlaylistApp class: addSongToPlaylist(), removeSongFromPlaylist(), playNextSong(), playPrevSong(), 
printCurrentSong()

## Phase 4: Task 3
- If I had more time, I would definitely refactor my PlaylistGUI class as the class does not follow the Single 
Responsibility Principle. For example, I would have split up the different GUI components such as the JPanel and
JMenuBar components. There also appears to be duplicate code which would require refactoring as well to prevent 
coupling.