#Prints a welcome message to the user.
def greet():
    print("Hi! Welcome to the flight booking application. How can I help you today?")

# Takes input from the user to book a flight, including departure city, destination city, departure date, and return date. It then displays a confirmation message.
def book_flight():
    print("Sure, let's book a flight for you. Can you please tell me your departure and destination cities?")
    departure = input("Departure city: ")
    destination = input("Destination city: ")
    print("Great! Now, can you tell me your travel dates?")
    departure_date = input("Departure date (YYYY-MM-DD): ")
    return_date = input("Return date (YYYY-MM-DD): ")
    print("Thank you! Your flight has been booked from {0} to {1} departing on {2} and returning on {3}.".format(departure, destination, departure_date, return_date))

# Takes input from the user to check the availability of flights between the departure and destination cities on a specific departure date.
def check_flight_availability():
    print("Sure, let me check the availability of flights for you. Can you please tell me your departure and destination cities?")
    departure = input("Departure city: ")
    destination = input("Destination city: ")
    print("Great! Now, can you tell me your travel dates?")
    departure_date = input("Departure date (YYYY-MM-DD): ")
    print("Flights are available from {0} to {1} on {2}.".format(departure, destination, departure_date))

# Takes input from the user to check the price of the flight for a specific departure and return date.
def get_flight_price():
    print("Sure, let me check the price of the flight for you. Can you please tell me your travel dates?")
    departure_date = input("Departure date (YYYY-MM-DD): ")
    return_date = input("Return date (YYYY-MM-DD): ")
    print("The price for your travel dates is $500.")

# Takes input from the user to book a hotel, including the destination city, check-in date, and check-out date. It then displays a confirmation message.
def book_hotel():
    print("Sure, let's book a hotel for you. Can you please tell me your destination city?")
    destination = input("Destination city: ")
    print("Great! Now, can you tell me your travel dates?")
    checkin_date = input("Check-in date (YYYY-MM-DD): ")
    checkout_date = input("Check-out date (YYYY-MM-DD): ")
    print("Thank you! Your hotel has been booked in {0} from {1} to {2}.".format(destination, checkin_date, checkout_date))

# Takes input from the user to cancel a booking based on a booking reference number.
def cancel_booking():
    print("Sorry to hear that you want to cancel your booking. Please provide me with your booking reference number.")
    booking_ref = input("Booking reference number: ")
    try:
        booking_ref = int(booking_ref)
    except ValueError:
        print("Invalid booking reference number.")
        return
    print("Your booking with reference number {0} has been cancelled.".format(booking_ref))

# Displays a closing message to the user.
def end():
    print("Thank you for using the flight booking application. Have a great day!")

#
greet()
while True:
    response = input("\nWhat would you like to do? (book a flight, check flight availability, get flight price, book hotel, cancel booking, exit): ")
    if "cancel" in response:
        cancel_booking()
    elif "hotel" in response:
        book_hotel()
    elif "book" in response:
        book_flight()
    elif "check" in response:
        check_flight_availability()
    elif "price" in response:
        get_flight_price()
    elif "exit" in response:
        end()
        break
    else:
        print("I'm sorry, I didn't understand that. Please try again.")
