import socket

serverIP = "127.0.0.1"
serverPort = 9011

print('PYTHON UDP CLIENT')
client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

msg_bytes = (300).to_bytes(4, 'little')
print(msg_bytes)
client.sendto(msg_bytes, (serverIP, serverPort))
buff, address = client.recvfrom(1024)
print("python udp server received msg: ", int.from_bytes(buff, 'little'))
