package com.theara.lab6;

import com.theara.lab6.model.*;
import com.theara.lab6.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class Lab6Application implements CommandLineRunner {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private DentistRepository dentistRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private SurgeryRepository surgeryRepository;

    public static void main(String[] args) {
        SpringApplication.run(Lab6Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        populateData();

    }

    private void populateData() {

        // Create and persist Address instances
        Address fairfieldAddress = new Address();
        fairfieldAddress.setAddress("Fairfield");
        addressRepository.save(fairfieldAddress);

        Address iowaCityAddress = new Address();
        iowaCityAddress.setAddress("Iowa City");
        addressRepository.save(iowaCityAddress);

        Address burlingtonAddress = new Address();
        burlingtonAddress.setAddress("Burlington");
        addressRepository.save(burlingtonAddress);

        Address burlingtonAddress2 = new Address();
        burlingtonAddress2.setAddress("Burlington");
        addressRepository.save(burlingtonAddress2);

        Address keokukAddress = new Address();
        keokukAddress.setAddress("Keokuk");
        addressRepository.save(keokukAddress);

        // Create and persist Dentist instances with dummy data
        Dentist tonySmith = new Dentist();
        tonySmith.setName("Tony Smith");
        tonySmith.setEmail("tony.smith@example.com"); // Dummy email
        tonySmith.setPhone("(555) 555-5555"); // Dummy phone number (US format)
        dentistRepository.save(tonySmith);

        Dentist helenPearson = new Dentist();
        helenPearson.setName("Helen Pearson");
        helenPearson.setEmail("helen.pearson@dentalclinic.com"); // Dummy email
        helenPearson.setPhone("(123) 456-7890"); // Dummy phone number (US format)
        dentistRepository.save(helenPearson);

        Dentist robinPlevin = new Dentist();
        robinPlevin.setName("Robin Plevin");
        robinPlevin.setEmail("robin.plevin@dentistry.org"); // Dummy email
        robinPlevin.setPhone("(987) 654-3210"); // Dummy phone number (US format)
        dentistRepository.save(robinPlevin);

        // Create and persist Surgery instances with dummy prices
        Surgery iowaCitySurgery = new Surgery();
        iowaCitySurgery.setName("Iowa City");
        iowaCitySurgery.setAddress(iowaCityAddress);
        iowaCitySurgery.setPrice(1500.00); // Dummy price (consider currency symbol)
        surgeryRepository.save(iowaCitySurgery);

        Surgery fairfieldSurgery = new Surgery();
        fairfieldSurgery.setName("Fairfield");
        fairfieldSurgery.setAddress(fairfieldAddress);
        fairfieldSurgery.setPrice(1250.50); // Dummy price (consider currency symbol)
        surgeryRepository.save(fairfieldSurgery);

        Surgery burlingtonSurgery = new Surgery();
        burlingtonSurgery.setName("Burlington");
        burlingtonSurgery.setAddress(burlingtonAddress);
        surgeryRepository.save(burlingtonSurgery);

        Surgery keokukSurgery = new Surgery();
        keokukSurgery.setName("Keokuk");
        keokukSurgery.setAddress(keokukAddress);
        surgeryRepository.save(keokukSurgery);

        // Create and persist Patient instances with dummy data
        Patient gillianWhite = new Patient();
        gillianWhite.setPatientId("P100");
        gillianWhite.setName("Gillian White");
        gillianWhite.setAddress(fairfieldAddress);
        gillianWhite.setEmail("gillian.white@example.com"); // Dummy email
        gillianWhite.setPhone("(555) 555-5555"); // Dummy phone number (US format)
        patientRepository.save(gillianWhite);

        Patient jillBell = new Patient();
        jillBell.setPatientId("P105");
        jillBell.setName("Jill Bell");
        jillBell.setAddress(burlingtonAddress);
        jillBell.setEmail("jill.bell@dentalclinic.com"); // Dummy email
        jillBell.setPhone("(123) 456-7890"); // Dummy phone number (US format)
        patientRepository.save(jillBell);

        Patient ianMacKay = new Patient();
        ianMacKay.setPatientId("P108");
        ianMacKay.setName("Ian MacKay");
        ianMacKay.setAddress(keokukAddress);
        ianMacKay.setEmail("ian.mackay@dentistry.org"); // Dummy email
        ianMacKay.setPhone("(987) 654-3210"); // Dummy phone number (US format)
        patientRepository.save(ianMacKay);

        Patient johnWalker = new Patient();
        johnWalker.setPatientId("P110");
        johnWalker.setName("John Walker");
        johnWalker.setAddress(burlingtonAddress2);
        johnWalker.setEmail("john.walker@example.net"); // Dummy email
        johnWalker.setPhone("(777) 777-7777"); // Dummy phone number (US format)
        patientRepository.save(johnWalker);

        // Create and persist Appointment instances
        createAppointment(tonySmith, gillianWhite, fairfieldSurgery, "12-Sep-13");
        createAppointment(tonySmith, jillBell, burlingtonSurgery, "12-Sep-13");
        createAppointment(helenPearson, ianMacKay, burlingtonSurgery, "12-Sep-13");
        createAppointment(helenPearson, ianMacKay, keokukSurgery, "14-Sep-13");
        createAppointment(robinPlevin, jillBell, burlingtonSurgery, "14-Sep-13");
        createAppointment(robinPlevin, johnWalker, burlingtonSurgery, "15-Sep-13");

    }

    private void createAppointment(Dentist dentist, Patient patient, Surgery surgery, String date) {
        Appointment appointment = new Appointment();
        appointment.setDentist(dentist);
        appointment.setPatient(patient);
        appointment.setSurgery(surgery);
        appointment.setDate(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MMM-yy")));
        appointmentRepository.save(appointment);

    }
}
