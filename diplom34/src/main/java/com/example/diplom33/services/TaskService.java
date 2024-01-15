package com.example.diplom33.services;

import com.example.diplom33.dto.TaskGetDTO;
import com.example.diplom33.enumeration.TaskStatus;
import com.example.diplom33.dto.TaskDTO;
import com.example.diplom33.models.Task;
import com.example.diplom33.models.User;
import com.example.diplom33.repositories.EmployeeRepository;
import com.example.diplom33.repositories.TaskRepository;
import com.example.diplom33.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;



//    @Transactional
//    public Optional<List<TaskGetDTO>> getAllTasksForUser(Principal principal) {
//        String phone = principal.getName();
//        User user = userRepository.findByPhone(phone).orElse(null);
//        updateAllTaskStatus();
//
//        List<Task> tasks = taskRepository.findByEmployeeRecipientAndStatusIn(user.getEmployee(), Arrays.asList(TaskStatus.IN_PROGRESS, TaskStatus.EXPIRED)).orElse(null);
//            return Optional.of(tasks.stream()
//                    .map(this::convertToTaskGetDTO)
//                    .collect(Collectors.toList()));
//
//    }

    @Transactional
    public Optional<List<TaskGetDTO>> getAllTasksForUser(Principal principal, String status) {
        String phone = principal.getName();
        User user = userRepository.findByPhone(phone).orElse(null);
        updateAllTaskStatus();

        List<TaskStatus> targetStatuses = switch (status) {
            case "in_progress" -> Arrays.asList(TaskStatus.IN_PROGRESS, TaskStatus.EXPIRED);
            case "completed" -> List.of(TaskStatus.COMPLETED);
            default -> null;
        };
        List<Task> tasks = taskRepository.findByEmployeeRecipientAndStatusIn(user.getEmployee(), targetStatuses).orElse(null);

        return Optional.ofNullable(tasks).map(taskList ->
                taskList.stream()
                        .map(this::convertToTaskGetDTO)
                        .collect(Collectors.toList())
        );

    }



//    public Optional<List<TaskGetDTO>> getAllTasksForUser(Principal principal,
//                                                         @RequestParam(name = "status", required = true) String status) {
//        String phone = principal.getName();
//        User user = userRepository.findByPhone(phone).orElse(null);
//        updateAllTaskStatus();
//
//        List<TaskStatus> targetStatuses;
//        switch (status) {
//            case "in_progress":
//                targetStatuses = Arrays.asList(TaskStatus.IN_PROGRESS, TaskStatus.EXPIRED);
//                break;
//            case "completed":
//                targetStatuses = List.of(TaskStatus.COMPLETED);
//                break;
//            default:
//                // По умолчанию возвращаем все задачи
//                targetStatuses = Arrays.asList(TaskStatus.values());
//                break;
//        }
//
//        List<Task> tasks = taskRepository.findByEmployeeRecipientAndStatusIn(user.getEmployee(), targetStatuses).orElse(null);
//
//        return Optional.ofNullable(tasks).map(taskList ->
//                taskList.stream()
//                        .map(this::convertToTaskGetDTO)
//                        .collect(Collectors.toList())
//        );
//    }

//    @Transactional
//    public Optional<List<Task>> getAllTasksForAdmin() {
//        updateAllTaskStatus();
//        return Optional.of(taskRepository.findAll());
//    }

//    @Transactional
//    public Optional<List<Task>> getCreatedTasks(Principal principal) {
//        String phone = principal.getName();
//        User user = userRepository.findByPhone(phone).orElse(null);
//        updateAllTaskStatus();
//
//        return taskRepository.findByEmployeeProducer(user.getEmployee());
//    }


    @Transactional
    public void createTask(TaskDTO taskDTO, Principal principal) {
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setComment(taskDTO.getComment());
        task.setExpiryDate(taskDTO.getExpiryDate());
        task.setStatus(TaskStatus.IN_PROGRESS);

        task.setEmployeeProducer(employeeRepository.findByUser(userRepository.findByPhone(principal.getName()).get()));
        task.setEmployeeRecipient(employeeRepository.findById(taskDTO.getRecipientId()).get());
        taskRepository.save(task);
    }

//    @Transactional
//    public void update(TaskDTO taskDTO, long id) {
//        Task task = taskRepository.findById(id).get();
//        BeanUtils.copyProperties(taskDTO, task, "id");
////        task.setEmployee(userRepository.findById(taskDTO.getEmployeeId()).get().getEmployee());
//        task.setEmployee(employeeRepository.findById(taskDTO.getEmployeeId()).get());
//        taskRepository.save(task);
//    }

    @Transactional
    public void updateAllTaskStatus() {
        List<Task> allTasks = taskRepository.findAll();
        for (Task task : allTasks) {
            if (task.getExpiryDate().isBefore(Instant.now()) && task.getStatus()!=TaskStatus.EXPIRED) {
                task.setStatus(TaskStatus.EXPIRED);
                taskRepository.save(task);
            }
            if(task.getExpiryDate().plus(Duration.ofDays(7)).isBefore(Instant.now())){
                taskRepository.delete(task);
            }
        }
    }

    private TaskGetDTO convertToTaskGetDTO(Task task) {
        TaskGetDTO dto = new TaskGetDTO();
        dto.setName(task.getName());
        dto.setExpiryDate(task.getExpiryDate());
        dto.setStatus(task.getStatus().name());
        dto.setRecipient(task.getEmployeeRecipient().getUser().getFullName());
        dto.setProducer(task.getEmployeeProducer().getUser().getFullName());
        return dto;
    }
}


//    @Transactional
//    public Optional<List<TaskGetDTO>> getAllTasksForUserCompleted(Principal principal) {
//        String phone = principal.getName();
//        User user = userRepository.findByPhone(phone).orElse(null);
//        updateAllTaskStatus();
//
//        List<Task> tasks = taskRepository.findByEmployeeRecipientAndStatusIn(user.getEmployee(), List.of(TaskStatus.COMPLETED)).orElse(null);
//        return Optional.of(tasks.stream()
//                .map(this::convertToTaskGetDTO)
//                .collect(Collectors.toList()));
//
//    }