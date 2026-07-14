package ir.maktabsharif.service;

import ir.maktabsharif.exception.BusinessException;
import ir.maktabsharif.exception.InvalidDataException;
import ir.maktabsharif.model.enums.Status;
import ir.maktabsharif.model.models.Event;
import ir.maktabsharif.repository.Impl.EventRepositoryImpl;

import java.util.List;
//Cancel Event
//When cancelling an event:
//• Change the event status to CANCELLED.
//• Existing reservations must remain unchanged.
//• No new reservations can be created for a cancelled event.


//لغو رویداد
//هنگام لغو یک رویداد:
//• وضعیت رویداد را به لغو شده تغییر دهید.
//• رزروهای موجود باید بدون تغییر باقی بمانند.
//• هیچ رزرو جدیدی برای یک رویداد لغو شده قابل ایجاد نیست.

public class EventLogic {
    //Create Event
    //Show All Events
    //Update Event
    //Cancel Event

    EventRepositoryImpl eventRepository = new EventRepositoryImpl();

    private boolean creatingOrUpdatingValidation(Event event){
        if (event.getTitle().isBlank() || event.getCapacity() < 0 || event.getTicket_price() < 0){
            return false;
        }
        return true;
    }

    public void CreateEvent(Event event){
        try {
            if (creatingOrUpdatingValidation(event)){
                if (eventRepository.Save(event)){
                    System.out.println("event add successfully");
                    System.out.println(event);
                }
            }else throw new BusinessException("Business Exception");
        }catch (Exception e){
            throw new BusinessException("Business Exception");
        }

    }

    public void ShowAllEvents(){
        List<Event> events = eventRepository.FIndAll();
        events.stream().forEach(System.out::println);
    }

    public void UpdateEvent(Event event){
        try {
            if (creatingOrUpdatingValidation(event)){
                Event event1 = eventRepository.Update(event);
                if (event1 != null){
                    System.out.println("Update Event successfully");
                    System.out.println(event1);
                }else {
                    System.out.println("something we wrong");
                }
            }else throw new BusinessException("Business Exception");
        }catch (Exception e){
            throw new InvalidDataException("Business Exception");
        }

    }

    public void CancelEvent(Long id){
        Event event = eventRepository.FindById(id);
        try {
            if (event != null){
                event.setStatus(Status.CANCELLED);
                eventRepository.Update(event);
            }else {
                System.out.println("Something we wrong");
            }
        }catch (Exception e){
            throw new BusinessException("Business Exception");
        }


    }

    public boolean isActive(Event event){
        Event event1 = eventRepository.FindById(event.getId());
        try {
            if (event1 != null){
                if (event1.getStatus().equals(Status.ACTIVE)){
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            throw new BusinessException("Business Exception");
        }

    }




}
