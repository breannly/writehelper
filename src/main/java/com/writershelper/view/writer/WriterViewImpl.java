package com.writershelper.view.writer;

import com.writershelper.controller.WriterController;
import com.writershelper.dto.writer.WriterCreateDto;
import com.writershelper.dto.writer.WriterUpdateDto;
import com.writershelper.exception.ItemNotFoundException;
import com.writershelper.exception.ValidationException;
import com.writershelper.model.Writer;
import com.writershelper.utils.ConsoleReader;

import java.io.IOException;

public class WriterViewImpl implements WriterView {

    private final WriterController writerController = new WriterController();

    @Override
    public void create() {
        try {
            System.out.println("Enter the author's first name: ");
            String firstName = ConsoleReader.read();
            System.out.println("Enter the author's last name: ");
            String lastName = ConsoleReader.read();

            WriterCreateDto writerCreateDto = new WriterCreateDto(firstName, lastName);
            Writer savedWriter = writerController.create(writerCreateDto);

            System.out.println("INFO: writer was successfully saved with id: " + savedWriter.getId());
        } catch (NumberFormatException e) {
            System.err.println("ERROR: Invalid post ID format");
        } catch (IOException e) {
            System.err.println("ERROR: data reading error");
        } catch (ValidationException | ItemNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void get() {
        try {
            System.out.println("Enter the author's id: ");
            Long writerId = Long.parseLong(ConsoleReader.read());

            Writer writer = writerController.get(writerId);

            System.out.println("Author's name is " + writer.getFirstName());
            System.out.println("Author's last name is " + writer.getLastName());
        } catch (NumberFormatException e) {
            System.err.println("ERROR: Invalid post ID format");
        } catch (IOException e) {
            System.err.println("ERROR: data reading error");
        } catch (ItemNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update() {
        try {
            System.out.println("Enter the author's id: ");
            Long writerId = Long.parseLong(ConsoleReader.read());
            System.out.println("Enter the new author's first name: ");
            String firstName = ConsoleReader.read();
            System.out.println("Enter the new second name: ");
            String lastName = ConsoleReader.read();

            WriterUpdateDto writerUpdateDto = new WriterUpdateDto(writerId, firstName, lastName);
            writerController.update(writerUpdateDto);
            System.out.println("INFO: writer was successfully updated");
        } catch (NumberFormatException e) {
            System.err.println("ERROR: Invalid post ID format");
        } catch (IOException e) {
            System.err.println("ERROR: data reading error");
        } catch (ValidationException | ItemNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete() {
        try {
            System.out.println("Enter the writer's id: ");
            Long writerId = Long.parseLong(ConsoleReader.read());

            writerController.delete(writerId);
            System.out.println("INFO: writer was successfully deleted");
        } catch (NumberFormatException e) {
            System.err.println("ERROR: Invalid post ID format");
        } catch (IOException e) {
            System.err.println("ERROR: data reading error");
        } catch (ItemNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
