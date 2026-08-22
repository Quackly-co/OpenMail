# OpenMail Development Plan

1. Implement a simple command line console when someone runs `java -jar openmail.jar`, that can be automated by running `java -jar openmail.jar -c command` (also add a simple way to add commands and a simple argument reading system). On first startup add a setup.
2. Implement a simple SMTP/IMAP/POP3 mail server without TLS or SSL, that can be started with the 1. step console command `start` optionally with the arguments `pop3 1234 imap 1234 smtp 1234` for ports, store data in a simple hashmap.
3. Implement data storing by storing data in the `.om` format, by using the style below and compressing it with Zlib Dynamic Huffman Coding, you may make changes to the file to make it more email accurate
```yaml
OpenMail: 0.1

From: Company <no-reply@company.com>
To: User2 <user2@anothercompany.com>
Mailed-by: company.com
Reply-To: Help Center <help@company.com>

HTML-Content: |
<a href="https://company.com/click-email?238468">Click Me</a>
Plain-Content: |
Go to https://company.com/click-email?238468
```
also store every email as a separate .om file, files will be stored in /etc/openmail/users/(emailfirstpart)/email.om or C:/Users/Username/.openmail/users/(emailfirstpart)/email.om etc
4. Implement security. By allowing user creation through the console, `create user "Full name" with email "fullname@company.com" with password "1234"`, then it will ask for the master string given to the user who set up the software (more said at the end of this step about the string), the username for the protocols is the users email and password is their password. Store user data in a .user.om file. Each .om file will be stored like this: The data will be encrypted using AES Encrypt CBC mode with a random key and IV will be a6a6a6a6a6a6a6a6a6a6a6a6a6a6a6a6, then it will be AES key wrapped twice with the same IV and key1 will be the password for the user whose files they are and key2 will be a random 32 byte string given to the user who set up the software which will be the same for every user. To decrypt do the reverse. Also add SSL/TLS and STARTTLS support.
5. Implement other QOL features, such as IMAP download (lets the user type in an IMAP username and password and domain+port and the program will download all emails under the respective user)