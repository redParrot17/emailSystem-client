# Email System - Client

Client side of a scratch built email system. This client is to be used in conjunction with the [server side](https://github.com/redParrot17/emailSystem-server) program.  
This client uses an insanely strong encryption for communicating with the server. And by strong, I mean signed 256 bit AES in conjunction with a 4096 bit RSA end-to-end encryption :)

## Feature List

These are the features this program currently supports along with features that are planned but not yet implemented.

- [x] Running the client from a computer seperate from the server
- [x] Logging into an existing account
- [x] Creating a new account
- [x] Overview page of all your received emails
- [x] Viewing individual emails
- [x] Creating and sending new emails
- [x] Deleting received emails
- [x] Show which emails are unread
* [ ] Logging back out
* [ ] Handling when the server shutsdown

Want to use the tcp server/client framework in your own project? You can find the dependency [HERE](https://github.com/redParrot17/redTCP).
