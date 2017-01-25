package shareevent

import scala.language.higherKinds
import org.joda.time.{DateTime, Duration => JodaDuration}
import shareevent.model._

import scala.util.Try

trait DomainService[M[_]] {

  

  def createEvent(organizer: Person,
                  title: String,
                  theme: String,
                  organizerCost: Money = Money.zero,
                  duration: JodaDuration = JodaDuration.standardHours(2),
                  scheduleWindow: JodaDuration = JodaDuration.standardDays(14),
                  quantityOfParticipants: Int = 5
                 ): DomainContext => M[Event]


  def createLocation(name: String,
                     capacity: Int,
                     startSchedule: DateTime,
                     endSchedule: DateTime,
                     coordination: Coordinate,
                     costs: Money): DomainContext => M[Location]


  /**
    * If participant is interested in event, he can participate
    * in scheduling of one.
    */
  def participantInterest(event:Event, participant: Person): (DomainContext) => M[Boolean]

  def schedule(eventId: Event.Id, locationId: Location.Id, time: DateTime): DomainContext => M[ScheduleItem]

  def locationConfirm(scheduleItem: ScheduleItem): DomainContext => M[ScheduleItem]

  def generalConfirm(scheduleItem: ScheduleItem): DomainContext => Confirmation

  def cancel(confirmation: Confirmation): DomainContext => M[Event]

  def run(confirmation: Confirmation): DomainContext => M[Event]

  def possibleLocationsForEvent(event:Event): DomainContext => M[Seq[ScheduleItem]]

  def possibleParticipantsInEvent(event: Event): DomainContext => M[Seq[Person]]


}
