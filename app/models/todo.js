import Ember from 'ember';
import Model from '@ember-data/model';
import { attr } from '@ember-data/model';

export default class TodoModel extends Model {
  @attr('string') title;
  @attr('string') body;
  @attr('string', {
    defaultValue() {
      return new Date();
    },
  }) created_at;
  @attr('boolean',{
    defaultValue() {
      return false;
    },
  }) completed;
}
