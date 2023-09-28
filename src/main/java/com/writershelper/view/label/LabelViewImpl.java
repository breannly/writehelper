package com.writershelper.view.label;

import com.writershelper.controller.LabelController;
import com.writershelper.dto.label.LabelCreateDto;
import com.writershelper.dto.label.LabelUpdateDto;
import com.writershelper.exception.ItemNotFoundException;
import com.writershelper.exception.ValidationException;
import com.writershelper.model.Label;
import com.writershelper.utils.ConsoleReader;

import java.io.IOException;

public class LabelViewImpl implements LabelView {

    private final LabelController labelController;

    public LabelViewImpl(LabelController labelController) {
        this.labelController = labelController;
    }

    @Override
    public void create() {
        try {
            System.out.println("Enter the label's name: ");
            String name = ConsoleReader.read();

            System.out.println("Enter the post's ID for the label: ");
            Long postId = Long.parseLong(ConsoleReader.read());

            LabelCreateDto labelCreateDto = new LabelCreateDto(name, postId);
            Label savedLabel = labelController.create(labelCreateDto);

            System.out.println("INFO: label was successfully saved with id: " + savedLabel.getId());
            System.out.println(savedLabel);
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
            System.out.println("Enter the label's id: ");
            Long labelId = Long.parseLong(ConsoleReader.read());

            Label label = labelController.get(labelId);

            System.out.println(label);
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
            System.out.println("Enter the label's id: ");
            Long labelId = Long.parseLong(ConsoleReader.read());

            System.out.println("Enter the new label's name: ");
            String name = ConsoleReader.read();

            LabelUpdateDto labelUpdateDto = new LabelUpdateDto(labelId, name);
            Label updatedLabel = labelController.update(labelUpdateDto);

            System.out.println("INFO: label was successfully updated");
            System.out.println(updatedLabel);
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
            System.out.println("Enter the label's id: ");
            Long labelId = Long.parseLong(ConsoleReader.read());

            Label deletedLabel = labelController.delete(labelId);

            System.out.println("INFO: label was successfully deleted");
            System.out.println(deletedLabel);
        } catch (NumberFormatException e) {
            System.err.println("ERROR: Invalid post ID format");
        } catch (IOException e) {
            System.err.println("ERROR: data reading error");
        } catch (ItemNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
