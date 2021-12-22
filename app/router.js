import Ember from 'ember';
import EmberRouter from '@ember/routing/router';
import config from 'todo-list/config/environment';

export default class Router extends EmberRouter {
  location = config.locationType;
  rootURL = config.rootURL;
}

Router.map(function () {
  this.route('todos', {resetNamespace: true}, function () {
    this.route('new');
    this.route('edit',{path:"/edit/:todo_id"});
  });
});
