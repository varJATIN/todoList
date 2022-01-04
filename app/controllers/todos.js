import Ember from 'ember';
//import Controller from '@ember/controller';

// export default class TodosController extends Controller {
export default Ember.Controller.extend({

	actions:{
		markComplete(todo) {
	      todo.completed = !todo.completed;
	      // return this.todo.completed;
	      todo.save();
	      console.log(todo);

	    }
	},
});
