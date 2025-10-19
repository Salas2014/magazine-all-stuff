terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "5.99.1"
    }
  }
}
provider "aws" {
  region = "us-east-1"
}

resource "aws_instance" "demo-server" {
  ami = "ami-0360c520857e3138f"
  instance_type = "t2.micro"
  key_name = "keyPairDemo"
  vpc_security_group_ids = [aws_security_group.demo-sg.id]
  subnet_id = aws_subnet.dpw-public_subent_01.id
  for_each = toset(["Jenkins-master", "build-slave", "ansible"])
  tags = {
    Name = "${each.key}"
  }
}

resource "aws_security_group" "demo-sg" {
  name        = "demo-sg"
  description = "SSH Access"
  vpc_id = aws_vpc.dpw-vpc.id

  ingress {
    description = "SSH Access"
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "Jenkins port"
    from_port = 8080
    to_port = 8080
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port = 0
    to_port = 0
    protocol = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "ssh-port"
  }
}


resource "aws_vpc" "dpw-vpc" {
  cidr_block = "10.1.0.0/16"
  tags = {
    Name = "dpw-vpc"
  }
}

resource "aws_subnet" "dpw-public_subent_01" {
  vpc_id                  = aws_vpc.dpw-vpc.id
  cidr_block              = "10.1.1.0/24"
  map_public_ip_on_launch = "true"
  availability_zone       = "us-east-1a"
  tags = {
    Name = "dpw-public_subent_01"
  }
}

resource "aws_subnet" "dpw-public_subent_02" {
  vpc_id                  = aws_vpc.dpw-vpc.id
  cidr_block              = "10.1.2.0/24"
  map_public_ip_on_launch = "true"
  availability_zone       = "us-east-1a"
  tags = {
    Name = "dpw-public_subent_02"
  }
}

resource "aws_internet_gateway" "dpw-igw" {
  vpc_id = aws_vpc.dpw-vpc.id
  tags = {
    Name = "dpw-igw"
  }
}

resource "aws_route_table" "dpw-public-rt" {
  vpc_id = aws_vpc.dpw-vpc.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.dpw-igw.id
  }
  tags = {
    Name = "dpw-public-rt"
  }
}

resource "aws_route_table_association" "dpw-rta-public-subent-1" {
  subnet_id      = aws_subnet.dpw-public_subent_01.id
  route_table_id = aws_route_table.dpw-public-rt.id
}

resource "aws_route_table_association" "dpw-rta-public-subent-2" {
  subnet_id      = aws_subnet.dpw-public_subent_02.id
  route_table_id = aws_route_table.dpw-public-rt.id
}

