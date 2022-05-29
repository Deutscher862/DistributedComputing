from SmartHome import DeviceTurnedOffError, InvalidColorError, ValueLimitReachedError


def run_loop(operations, device_prx, device_name):
    help(operations)
    while True:
        try:
            operation = input(f"{device_name}> ")
            if operation in operations:
                if operation == "exit":
                    break
                elif operation == "help":
                    help(operations)
                else:
                    operations[operation](device_prx)
            else:
                print("That is not a valid operation")
        except DeviceTurnedOffError as e:
            print('Invalid operation: ', e.errorMessage)
            print()
        except InvalidColorError as e:
            print('Invalid color: ', e.errorMessage)
            print()
        except ValueLimitReachedError as e:
            print('Value Limit Reached: ', e.errorMessage)
            print()


def help(operations):
    print("Available operations:")
    for operation in operations:
        print(operation)
