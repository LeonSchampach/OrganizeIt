# AWS EC2 Instructions

## Connect to EC2 via ssh

1. Open CMD or PowerShell
2. Run the SSH command:

```shell
ssh -i "C:\Users\Leon\Desktop\AWS\private keys\organizeit_instance\keypair1.pem" ec2-user@ec2-51-20-185-24.eu-north-1.compute.amazonaws.com
```

`C:\Users\Leon\Desktop\AWS\private keys\organizeit_instance\keypair1.pem`: Path to the keypair file.

`ec2-user@ec2-51-20-185-24.eu-north-1.compute.amazonaws.com`: User of the ec2 machine and the public IPv4 dns.

In case of problems with connecting to the ec2 machine, try changing the inbound security rules to allow ssh access from every IP-address.

## Pull & Run the Docker Image

SSH into your EC2 instance and run the following commands:

1. Pull the image from Docker Hub:

```shell
sudo docker pull retzino/organizeit:latest
```

2. Run the Docker container:

```shell
sudo docker run -d --name organizeit -p 8080:8080 retzino/organizeit:latest
```

## Create a Systemd Service File

This Service will start a Docker container automatically on startup.

1. Create a new service file for your Docker container

```shell
sudo nano /etc/systemd/system/organizeit.service
```

2. Add the following content to the service file:

```ini
[Unit]
Description=My Docker Container Service
After=network.target docker.service
Requires=docker.service

[Service]
Restart=always
ExecStart=/usr/bin/docker run --name myapp-container -p 8080:80 mydockerhubusername/myapp:latest
ExecStop=/usr/bin/docker stop -t 2 myapp-container
ExecStopPost=/usr/bin/docker rm -f myapp-container

[Install]
WantedBy=multi-user.target
```

- `After=network.target docker.service`: Ensures the network and Docker services are available before starting the container.
- `Requires=docker.service`: Ensures the Docker service is running.
- `Restart=always`: Ensures the service restarts if it fails.
- `ExecStart`: The command to run the Docker container.
- `ExecStop`: The command to stop the Docker container.
- `ExecStopPost`: The command to remove the Docker container after stopping it.

3. Save and close the file (in nano, press CTRL+X, then Y, then Enter).

## Reload Systemd and Enable the Service

1. Reload the systemd daemon to recognize the new service file:

```shell
sudo systemctl daemon-reload
```

2. Enable the service to start at boot:

```shell
sudo systemctl enable organizeit.service
```

3. Start the service immediately:

```shell
sudo systemctl start organizeit.service
```

### Command to restart the service

```shell
sudo systemctl restart organizeit.service
```