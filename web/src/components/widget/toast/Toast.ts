export default class Toast{
  show: boolean;
  status: string;
  msg: string;
  detail: string;
  constructor(show: boolean = false,  status: string = '',  msg: string = '',  detail: string = ''){
    this.show = show;
    this.status = status;
    this.msg = msg;
    this.detail = detail;
  }

  success(msg: string): void{
    this.show = true;
    this.status = 'success';
    this.msg = msg;
  }

  error(msg: string, detail: string): void{
    this.show = true;
    this.status = 'error';
    this.msg = msg;
    this.detail = detail;
  }
}
