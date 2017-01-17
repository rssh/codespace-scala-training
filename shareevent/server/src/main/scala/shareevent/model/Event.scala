package shareevent.model

import org.joda.time.DateTime
import org.joda.time.{Duration=>JodaDuration}

case class Event(
                title: String,
                theme: String,
                organizer: Organizer,
                organizerCost: Money,
                status: EventStatus,
                created: DateTime,
                scheduleWindow: JodaDuration
                )