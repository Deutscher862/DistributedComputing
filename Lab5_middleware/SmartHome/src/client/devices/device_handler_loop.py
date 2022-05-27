def run_loop(operations, device_prx, device_name):
    print("Choose operation: ")
    for operation in operations:
        print(operation)
    while True:
        operation = input(f"{device_name}> ")
        if operation in operations:
            if operation == "exit":
                break
            else:
                operations[operation](device_prx)
        else:
            print("That is not a valid operation")
