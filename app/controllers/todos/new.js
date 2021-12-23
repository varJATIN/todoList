import Ember from 'ember';
import Controller from '@ember/controller';

export default Ember.Controller.extend({
  actions: {
    addTodo() {
      var title = this.title;
      var date = this.date;
      var body = this.body;
      var todo = this.store.createRecord('todo', {
        title: title,
        created_at: date,
        body: body,
      });
      todo.save();
    },
  },
});
