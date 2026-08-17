# OpenMail

OpenMail is a **free, non-commercial, self-hosted mail server** built with Java. It provides the core protocols needed to run your own mail infrastructure without relying on a third-party mail provider.

OpenMail is designed to be lightweight, configurable, and easy to self-host. It currently focuses on the server side of email and does **not** include a web-based user interface.

## Features

* **SMTP** — Send and receive email.
* **IMAP** — Access and manage mailboxes from compatible email clients.
* **POP3** — Retrieve messages using compatible email clients.
* **Self-hosted** — Run your own mail server on your own infrastructure.
* **Java-based** — Built with Java for portability across operating systems.
* **No web UI** — OpenMail focuses on mail protocols rather than providing a browser-based interface.
* **Cross-platform** — Designed to run on Windows, Linux, and macOS.
* **Open for modification** — The source code can be modified for personal and non-commercial use.

## Requirements

To build OpenMail from source, you will need:

* A GitHub account
* A computer or server capable of running Java
* A supported Java development environment
* Internet access for downloading the source code and dependencies

> **Note:** OpenMail is currently intended to be built from source. Pre-built releases and automated installers may be provided in the future.

## Building OpenMail

Because OpenMail is currently distributed as source code, you must build it before running the server.

### 1. Fork OpenMail

Start by creating your own fork of the OpenMail repository.

[![Fork](https://img.shields.io/badge/Fork-Fork--?logo=github\&logoColor=FFFFFF\&color=gray)](https://github.com/Quackly-co/OpenMail/fork)

After clicking **Fork**, select your GitHub account or organization as the destination.

### 2. Build OpenMail

Open the **Actions** Tab in your fork and find *Global*.

Then find **Run Workflow** or similar and click it.

### 4. Run OpenMail

Once OpenMail has been built, start it using Java:

```bash
java -jar OpenMail.jar
```

You can provide additional command-line arguments when starting the server:

```bash
java -jar OpenMail.jar --help
```

## Self-Hosting

OpenMail is intended to be run on infrastructure that you control.

For a production deployment, you will generally need:

* A domain name
* A server with a stable public IP address
* DNS configuration
* Appropriate firewall rules
* TLS certificates
* Correct SMTP, IMAP, and/or POP configuration
* Appropriate DNS email records such as MX, SPF, DKIM, and DMARC

> **Important:** Running a public mail server requires careful configuration. Incorrect DNS, authentication, TLS, or spam-prevention settings can result in mail being rejected or your server being abused.

## Supported Platforms

OpenMail is written in Java and is intended to support multiple operating systems, including:

* LINUX   | SUPPORTED
* MACOS   | SUPPORTED
* WINDOWS | SUPPORTED

The exact supported Java versions and operating-system versions may change as development progresses.

## Command Line

OpenMail is designed to be usable from the command line.

The command-line interface will allow OpenMail to be easily integrated into servers, scripts, containers, and system services.

## Development

OpenMail is actively developed and may change significantly between versions.

If you want to contribute, you can fork the repository, make your changes, and submit a pull request.

Please make sure that changes are tested before submitting a pull request.

## License

OpenMail is intended to be **free for personal, educational, and non-commercial self-hosting**.

The project allows users to inspect and modify the source code subject to the terms of the OpenMail license.

Modified versions must clearly identify that they are modified versions of OpenMail and must not imply that they are official releases produced or endorsed by the original OpenMail project.

Commercial distribution or sale of OpenMail or modified versions is not permitted without explicit permission from the copyright holder.

See [`LICENSE`](LICENSE) for the complete terms and conditions.

## Disclaimer

OpenMail is provided for self-hosting and development purposes. Running an email server on the public internet requires appropriate security, DNS, abuse prevention, monitoring, and system administration practices.

You are responsible for configuring and operating your OpenMail installation securely and in compliance with applicable laws and the policies of your hosting provider and network.

## Project Status

OpenMail is currently under development.

Features, configuration formats, command-line options, and deployment procedures may change as the project evolves.

More documentation will be added as the server matures.

## Contributing

Contributions, bug reports, suggestions, and improvements are welcome.

If you find a problem with OpenMail, please open an issue with enough information to reproduce the problem.

When submitting a pull request, please describe:

* What was changed
* Why the change was necessary
* How the change was tested
* Any configuration or compatibility considerations

---

**OpenMail** — self-host your own mail.
