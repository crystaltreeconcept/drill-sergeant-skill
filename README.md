# Concept

Drill Sergeant allows tracking of Tasks (routine or assignments).

* Some users can define tasks for other users (TODO: Hierarchy of users)
* All users can define tasks for themselves
* Once task is defined - it's being tracked
* Tracked tasks trigger notifications along the lifetime, those 
reminders are grouped by person involved (TODO: consider triggering 
notification for issuer, not just for assignee)
* Status for a specific User can be requested by him or some other Users
* Tasks tracking works as a reminder what to do next, also allows to 'report 
and forget' for things that need to be done once (e.g. vitamins, or one-time 
duties you planned to do and not sure if those were done) 
* Task can be resolved by either completion or cancellation (TODO: consider 
re-scheduling, but for now it should hang forever until cancelled/resolved)
* Notification: "**Zlata**, report in status of **_teeth brushing_** that 
needs to be done in 20 minutes"
* Notification: "**Zlata**, report in status of **_teeth brushing_** that is 
12 minutes overdue"
* Task resolution: "_reporting in **Zlata** done_ _**teeth brushing**_"
* Task resolution: "_reporting in **Zlata** can't do_ _**teeth brushing**_"
* Task status change is logged, for both routine (recurring entries separately) 
and assignments

TODO: priorities in tasks:  (Set[Task]) => Stream[Task]
TODO: issue assignments in already completed state, for tracking (to track situations "please remember I already took my pill today")
TODO: report on past period
TODO: brief status report, verbose status report (including routines definition)



# Actors
## Soldier
Any user that can have tasks assigned to him, can report task status, can 
report for duty (request report on pending tasks)


## General extends Soldier
Any user that can issue and control tasks and monitor statuses for Soldiers

# Use Cases

## 0 Soldier creates new Assignment for themselves
Can create new assignment for self. This assignment is invisible to anyone except himself. Soldier will get 
reminders, and will get info as part of report and current status.
Context/params:
* logged in Soldier - assignment will go to this Soldier
* assignment sentence
### Examples
- Alexa, tell Drill Sergeant that Dima has to <do eyedrops> <at 8:30PM>
- Affirmative

- Alexa, tell Drill Sergeant that Doma has to <register himself> <before 9:00AM>
- Who is that?

## 1 General issues new Assignment for Soldier

## 1 Soldier gets reminder on the task
'<Zlata>, you need to do <toothbrushing> before 8:00AM, that is in <40 minutes>'

## 0 Soldier reports in and gets status of his tasks

### Example 1
- Drill Sergeant, Dima reporting in
- Dima, your tasks are: <take vitamins> <by 8:00PM>; <do eyedrops> <on 8:30PM>. Report in
- Affirmative
- Carry on 

### Example 2
- "Alexa, ask drill sergeant what I need to do today"
- what's your name, rookie?
- Zlata
- your assignments are: do dishes before 7:00PM, brush teeth before 7:30PM, watch cartoons before 5:30PM


## 

# Entities

## 0 Task
Can be Routine occurrence (TBD: give it a name), or Assignment. Has status (issued/'in progress'/done/cancelled)
Belongs to single specific 'doer', who has to perform the task
### Attribute: Sentence
in a form 'Zlata needs to do <toothbrushing paying attention to braces>'
### Attribute: [time constraint](#Time-constraint)
### Attribute: time issued
### TBD: attribute: type (this would affect reminding pattern)
### Relation: Soldier
### TBD: Relation: Issuer (General)

## 0 Assignment extends Task
One-time assignment
### Lifetime:
#### {issue} -> [active] (part of status, getting reminders) -> {complete} -> [complete] (Soldier reports in task as done/cancelled)

## 0 Time constraint
### 'Today' can be skipped when today's future time is used
### Examples:
"before 5:00PM"
"on 7:30PM"
"after 4:00PM on May-24"
"due" (meaning it's for 'since' some point in time in the past, but has no end date)


## 1 Routine
* Routine is a Repeated Task on some recurrent pattern. Single occurrence can be viewed as assignment.
* Occurrences are instantiated on demand, i.e. "Give me next 3 tasks" might include two occurrences of routine 
(including one in progress) and one assignment






# Example scenarios
- "Alexa, ask drill sergeant what I need to do today"
- what's your name, rookie?
- Zlata
- your assignments are: do dishes before 7:00PM, brush teeth before 7:30PM, watch cartoons before 5:30PM

- "Alexa, ask drill sergeant"
- what's your name, rookie?

- "Alexa, ask polite sergeant what Zlata has to do"
- Brush the teeth (report in before 7:30PM), submit her homework (report in 8:00PM), any questions?
- Sir, no, sir

